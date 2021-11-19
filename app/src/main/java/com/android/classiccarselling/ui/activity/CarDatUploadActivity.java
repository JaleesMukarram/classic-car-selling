package com.android.classiccarselling.ui.activity;

import static com.android.classiccarselling.global.Constants.CARS_COLLECTION;
import static com.android.classiccarselling.global.Constants.CARS_IMAGES_REFERENCE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.classiccarselling.R;
import com.android.classiccarselling.databinding.ActivityCarDatUploadBinding;
import com.android.classiccarselling.interfaces.CustomHooks;
import com.android.classiccarselling.model.Car;
import com.android.classiccarselling.model.StorageImage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarDatUploadActivity extends AppCompatActivity implements CustomHooks {

    private static final String TAG = "CarDatUploadActivityTAG";
    private ActivityCarDatUploadBinding binding;
    private StorageImage storageImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_car_dat_upload);

        callHooks();
    }

    @Override
    public void callHooks() {

        initViews();
        initListeners();
    }

    @Override
    public void handleIntent() {

    }

    @Override
    public void initViews() {

    }

    @Override
    public void initListeners() {

        binding.btn.setOnClickListener(view -> {

            String brand = binding.etBrand.getText().toString().trim();
            String name = binding.etName.getText().toString().trim();
            String year = binding.etYear.getText().toString().trim();
            String registrationYear = binding.etRegistration.getText().toString().trim();
            String color = binding.etColor.getText().toString().trim();
            String km = binding.etKM.getText().toString().trim();
            double price = Double.parseDouble(binding.etPrice.getText().toString().trim());

            List<StorageImage> carImages = new ArrayList<>();
            carImages.add(storageImage);

            Car car = new Car(
                    brand,
                    name,
                    year,
                    registrationYear,
                    color,
                    km,
                    price,
                    carImages
            );

            FirebaseFirestore.getInstance().collection(CARS_COLLECTION)
                    .document(car.getId())
                    .set(car).addOnCompleteListener(task -> {

                if (!task.isSuccessful()) {

                    Log.e(TAG, "initListeners: ", task.getException());
                    Toast.makeText(this, "Failed " + task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Log.d(TAG, "initListeners: ready for new");
                Toast.makeText(this, "Car Added", Toast.LENGTH_SHORT).show();

            });

        });

        binding.ivPic.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 100);
        });
    }

    @Override
    public void observe() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK) {


            String id = UUID.randomUUID().toString();

            StorageReference reference = FirebaseStorage.getInstance().getReference(CARS_IMAGES_REFERENCE).child(id);

            reference
                    .putFile(data.getData())
                    .addOnCompleteListener(task -> {

                        if (!task.isSuccessful()) {

                            Log.e(TAG, "onActivityResult: ", task.getException());
                            return;
                        }

                        reference.getDownloadUrl().addOnCompleteListener(task1 -> {

                            this.storageImage = new StorageImage(
                                    id,
                                    task1.getResult().toString(),
                                    reference.getPath()
                            );

                            Log.d(TAG, "onActivityResult: image added");
                            Bitmap bitmap;

                            try {
                                bitmap = MediaStore
                                        .Images
                                        .Media
                                        .getBitmap(
                                                getContentResolver(),
                                                data.getData());
                                binding.ivPic.setImageBitmap(bitmap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    });
        } else {

            Toast.makeText(this, "Image not selected", Toast.LENGTH_SHORT).show();
        }
    }
}