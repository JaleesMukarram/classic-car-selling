package com.android.classiccarselling.viewmodel;

import static com.android.classiccarselling.global.Constants.BRANDS_COLLECTION;
import static com.android.classiccarselling.global.Constants.CARS_COLLECTION;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.classiccarselling.model.Brand;
import com.android.classiccarselling.model.Car;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainVM extends ViewModel {

    private final FirebaseAuth firebaseAuth;
    private final FirebaseFirestore db;
    public final MutableLiveData<List<Car>> cars = new MutableLiveData<>();
    public final MutableLiveData<List<Brand>> brands = new MutableLiveData<>();

    public MainVM() {

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    public void getAllCars() {

        db.collection(CARS_COLLECTION)
                .get()
                .addOnCompleteListener(task -> {

                    if (!task.isSuccessful()) {

                        return;
                    }

                    List<Car> carList = new ArrayList<>();

                    for (DocumentSnapshot snapshot : Objects.requireNonNull(task.getResult())) {

                        Car car = snapshot.toObject(Car.class);
                        carList.add(car);
                    }

                    cars.setValue(carList);
                });
    }

    public void getBrands() {

        db.collection(BRANDS_COLLECTION)
                .get()
                .addOnCompleteListener(task -> {

                    if (!task.isSuccessful()) {

                        return;
                    }

                    List<Brand> brandList = new ArrayList<>();

                    for (DocumentSnapshot snapshot : Objects.requireNonNull(task.getResult())) {

                        Brand car = snapshot.toObject(Brand.class);
                        brandList.add(car);
                    }

                    brands.setValue(brandList);
                });
    }
}
