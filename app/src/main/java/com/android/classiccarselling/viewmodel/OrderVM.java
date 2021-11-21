package com.android.classiccarselling.viewmodel;

import static com.android.classiccarselling.global.Constants.CART_COLLECTION;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class OrderVM extends ViewModel {

    private final FirebaseAuth firebaseAuth;
    private final FirebaseFirestore db;

    public MutableLiveData<Boolean> addedInCart = new MutableLiveData<>();
    public MutableLiveData<Exception> error = new MutableLiveData<>();

    public OrderVM() {

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

    }

    public void addThisCarToMyCart(String carId) {

        db.collection(CART_COLLECTION)
                .document(firebaseAuth.getUid())
                .update("carIds", FieldValue.arrayUnion(carId))
                .addOnCompleteListener(task -> {

                    if (!task.isSuccessful()) {

                        error.setValue(task.getException());
                        return;
                    }

                    addedInCart.setValue(true);
                });
    }

    public void removeThisCarFromMyCart(String carId) {

        db.collection(CART_COLLECTION)
                .document(firebaseAuth.getUid())
                .update("carIds", FieldValue.arrayRemove(carId))
                .addOnCompleteListener(task -> {

                    if (!task.isSuccessful()) {

                        error.setValue(task.getException());
                        return;
                    }

                    addedInCart.setValue(false);
                });
    }
}