package com.example.muleropedrosa.pr_tarea3muleropedrosabenjamin.fragmento;


import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;
import com.example.muleropedrosa.pr_tarea3muleropedrosabenjamin.R;


public class AjustesFragment extends PreferenceFragmentCompat {


    public AjustesFragment() {

    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String key) {
        setPreferencesFromResource(R.xml.root_preferences,key);
    }

}