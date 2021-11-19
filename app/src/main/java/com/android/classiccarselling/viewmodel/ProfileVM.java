package com.android.classiccarselling.viewmodel;

import static com.android.classiccarselling.global.Constants.USER_INFO_COLLECTION;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.classiccarselling.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.Objects;

public class ProfileVM extends ViewModel {

    private static final String TAG = "ProfileVMTAG";

    private final FirebaseAuth firebaseAuth;
    private final FirebaseFirestore db;

    public final MutableLiveData<User> updatedUser = new MutableLiveData<>();
    public final MutableLiveData<User> defaultUser = new MutableLiveData<>();

    public final MutableLiveData<Exception> error = new MutableLiveData<>();

    public ProfileVM() {

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

    }

    public void saveUserInfo(String name, String city) {

        String id = firebaseAuth.getUid();
        User user = new User(id, name, city);

        db.collection(USER_INFO_COLLECTION)
                .document(user.getId())
                .set(user, SetOptions.merge())
                .addOnCompleteListener(task -> {

                    if (!task.isSuccessful()) {

                        error.setValue(task.getException());
                        return;
                    }

                    updatedUser.setValue(user);
                });
    }

    public void getSavedUser() {

        db.collection(USER_INFO_COLLECTION)
                .document(Objects.requireNonNull(firebaseAuth.getUid()))
                .get().addOnCompleteListener(task -> {

            if (!task.isSuccessful()) {

                return;
            }

            User user = Objects.requireNonNull(task.getResult()).toObject(User.class);

            if (user != null) {

                defaultUser.setValue(user);
            }
        });
    }
}
