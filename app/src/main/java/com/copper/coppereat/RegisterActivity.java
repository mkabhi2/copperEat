package com.copper.coppereat;

import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegisterActivity extends AppCompatActivity  implements View.OnClickListener {

    UserSession userSession;

    private EditText nameEditText;
    private EditText mobileEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    Button showPasswordButton;
    private Button signUpButton;
    Toolbar registerActivityToolbar;
    ProgressBar registerProgressBar;
    String emailID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userSession=new UserSession(this);

        registerActivityToolbar = (Toolbar) findViewById(R.id.registerPageToolbar);
        nameEditText = (EditText) findViewById(R.id.registerNameEditText);
        mobileEditText = (EditText) findViewById(R.id.registerMobileEditText);
        emailEditText = (EditText) findViewById(R.id.registerEmailEditText);
        passwordEditText = (EditText) findViewById(R.id.registerPassworddEditText);
        showPasswordButton = (Button) findViewById(R.id.registerShowPasswordButton);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        registerProgressBar=(ProgressBar)findViewById(R.id.registerProgressBar);


        registerActivityToolbar.setTitle("SIGN UP");

        registerProgressBar.setVisibility(View.GONE);
        showPasswordButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);

        passwordEditText.setTransformationMethod(new AsteriskTransformationMethod());


        Bundle bundle = getIntent().getExtras();
        emailID = bundle.getString("email");

        emailEditText.setText(emailID);
        emailEditText.setEnabled(false);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.registerShowPasswordButton:
                if (showPasswordButton.getText().equals("HIDE")) {
                    showPasswordButton.setText("SHOW");
                    passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordEditText.setSelection(passwordEditText.getText().length());
                } else if (showPasswordButton.getText().equals("SHOW")) {
                    showPasswordButton.setText("HIDE");
                    passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passwordEditText.setSelection(passwordEditText.getText().length());
                    break;
                }

            case R.id.signUpButton:
                if (validateSignUpFields()) {

                } else {

                }
        }
    }

    public boolean validateSignUpFields() {

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String name = nameEditText.getText().toString();
        String mobileNumber = mobileEditText.getText().toString();
        if (UtilityClass.validate(email) && password != null && password.length() > 5 && name != null && mobileNumber != null && mobileNumber.length() == 10 && (mobileNumber.startsWith("9") || mobileNumber.startsWith("8") || mobileNumber.startsWith("7"))) {
            registerProgressBar.setVisibility(View.VISIBLE);
            new ExecuteTask().execute(email, password, name, mobileNumber);
            return true;
        } else {
            return false;
        }
    }

    private class ExecuteTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {

            String type = "application/json";
            String s = "";
            try {

                HttpURLConnection httpURLConnection = null;
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL("http://18.220.28.118:8080/messenger/webapi/customers");//vs02277@gmail.com");
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("Content-Type", type);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.connect();

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("customerEmail", params[0]);
                    jsonObject.put("password", params[1]);
                    jsonObject.put("customerFirstName", params[2]);
                    jsonObject.put("customerMobileNumber", params[4]);


                    DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                    wr.writeBytes(jsonObject.toString());
                    wr.flush();
                    wr.close();

                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(httpURLConnection.getInputStream()));
                        StringBuffer sb = new StringBuffer("");
                        String line = "";
                        while ((line = in.readLine()) != null) {
                            sb.append(line);
                            //break;
                        }

                        in.close();
                        return sb.toString();
                    }
                } catch (Exception e) {

                }
            } catch (Exception exception) {
            }
            return s;
        }

        @Override
        protected void onPostExecute(String result) {
            registerProgressBar.setVisibility(View.GONE);
            if (result.contains("true")) {
                userSession.setLoggedIn(true);
                Toast.makeText(RegisterActivity.this, "Succesfully registerd", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegisterActivity.this, "Please Fill mandatory fields", Toast.LENGTH_SHORT).show();
            }
        }


    }
}