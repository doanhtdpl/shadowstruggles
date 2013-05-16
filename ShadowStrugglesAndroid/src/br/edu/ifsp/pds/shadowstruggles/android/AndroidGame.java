package br.edu.ifsp.pds.shadowstruggles.android;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;

import com.badlogic.gdx.backends.android.AndroidApplication;

public class AndroidGame extends AndroidApplication {
        public void onCreate (android.os.Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                initialize(new ShadowStruggles(), false);
        }
}
