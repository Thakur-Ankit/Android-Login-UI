package com.mca.ankitak.uiloginscreen;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.solver.widgets.ConstraintTableLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout mylayout;
    private Button animateBTN;
    ConstraintLayout layout1, layout2;
    TextView regnTV, gotoCN;
    Button regnBTN, signinBTN;
    EditText emailEDT, passEDT;
    Boolean state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mylayout = findViewById(R.id.constraintLayout);
        animateBTN = findViewById(R.id.button);
        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);

        regnTV = findViewById(R.id.registerTV);
        gotoCN = findViewById(R.id.gotoCN);
        regnBTN = findViewById(R.id.registerBTN);
        signinBTN = findViewById(R.id.signinBTN);

        emailEDT = findViewById(R.id.emailEDT);
        passEDT = findViewById(R.id.passEDT);

        passEDT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (checkEDT()) {
                    //visible sign in button after some value is entered in Password Edittext
                    signinBTN.setVisibility(View.VISIBLE);
                } else {
                    signinBTN.setVisibility(View.INVISIBLE);
                }
            }
        });

        layout1.bringToFront();

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.bringToFront();
                regnBTN.setVisibility(View.INVISIBLE);
                gotoCN.setVisibility(View.VISIBLE);
                //Assign a new value for layout1 at runtime
                ConstraintLayout.LayoutParams layoutParams1 = (ConstraintLayout.LayoutParams) layout1.getLayoutParams();
                layoutParams1.height = dpToPx(320);
                layoutParams1.width = dpToPx(320);
                layout1.setLayoutParams(layoutParams1);

                //Assign a new value for layout2 at runtime
                ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layout2.getLayoutParams();
                layoutParams2.height = dpToPx(300);
                layoutParams2.width = dpToPx(300);
                layout2.setLayoutParams(layoutParams2);
                v.setZ(20);
                layout2.setZ(0);
            }
        });

        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.bringToFront();
                regnBTN.setVisibility(View.VISIBLE);
                gotoCN.setVisibility(View.INVISIBLE);

                //Assign a new value for layout2 at runtime
                ConstraintLayout.LayoutParams layoutParams1 = (ConstraintLayout.LayoutParams) layout2.getLayoutParams();
                layoutParams1.height = dpToPx(320);
                layoutParams1.width = dpToPx(320);
                layout2.setLayoutParams(layoutParams1);

                //Assign a new value for layout1 at runtime
                ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layout1.getLayoutParams();
                layoutParams2.height = dpToPx(300);
                layoutParams2.width = dpToPx(300);
                layout1.setLayoutParams(layoutParams2);
                v.setZ(20);
                layout1.setZ(0);
            }
        });
    }

    //check Edittext is null or not
    public boolean checkEDT() {
        if (emailEDT != null && passEDT != null) {
            state = false;
        }
        state = true;

        return state;
    }

    //convert layout size from dp to pixels
    public int dpToPx(int dp) {
        float density = this.getResources()
                .getDisplayMetrics()
                .density;
        return Math.round((float) dp * density);
    }

    //on click sign in button
     public void signin(View view) {
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(1.3, 24.5);
        myAnim.setInterpolator(interpolator);
        animateBTN.startAnimation(myAnim);
        mylayout.setVisibility(View.INVISIBLE);
        layout1.setVisibility(View.INVISIBLE);
        layout2.setVisibility(View.INVISIBLE);

        final Handler handler = new Handler();
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1300);
                } catch (Exception e) {
                }
                handler.post(new Runnable() {
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, FinalActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
            }
        }).start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
