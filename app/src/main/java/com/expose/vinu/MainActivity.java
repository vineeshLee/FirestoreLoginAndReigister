package com.expose.vinu;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.expose.vinu.custom_font.MyEditText;
import com.expose.vinu.custom_font.MyTextView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button bt_add,bt_show;
    FirebaseFirestore db;
    TextView tv_details;
    List<Users> Users_LIST;TextView holliday;
    MyTextView create,getstarted;
    MyEditText username,password;



    public static  final  String COLLECTION_NAME_KEY = "USERS";
    public static  final  String USERNAME_KEY = "username";
    public static  final  String PASSWORD_KEY = "password";
    public static  final  String NUMBER_KEY = "phoneNo:";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        db = FirebaseFirestore.getInstance();

        create = findViewById(R.id.createaccount);
        getstarted = findViewById(R.id.getstarted);
        holliday =findViewById(R.id.holliday);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(MainActivity.this,MainActivity1.class);
                startActivity(it);
            }
        });

        // Access a Cloud Firestore instance from your Activity











        getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (!username.getText().toString().equals("") && !password.getText().toString().equals(""))
                {

                    DocumentReference docRef = db.collection(COLLECTION_NAME_KEY).document(username.getText().toString());
                    docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {

                            if (documentSnapshot.exists())
                            {


                                Users user = documentSnapshot.toObject(Users.class);

                                if (user.getPassword().equals(password.getText().toString()))
                                {

                                    Toast.makeText(getApplicationContext(), "welcome", Toast.LENGTH_SHORT).show();
                                    Intent it = new Intent(MainActivity.this,ScrollingActivity.class);
                                    startActivity(it);
                                }

                                else
                                {
                                    Toast.makeText(MainActivity.this, "Passsword Mismatching", Toast.LENGTH_SHORT).show();
                                }





                            }

                            else
                            {

                                Toast.makeText(getApplicationContext(), "Check your Username ", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

                else

                {


                    Toast.makeText(MainActivity.this, "Username or Password Cannot be Empty", Toast.LENGTH_SHORT).show();
                }





              /*  DocumentReference docRef = db.collection(COLLECTION_NAME_KEY).document();

                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {



                        if (documentSnapshot.exists())
                        {

                               Toast.makeText(getApplicationContext(), "welcome", Toast.LENGTH_SHORT).show();
                                Intent it = new Intent(MainActivity.this,ScrollingActivity.class);
                                startActivity(it);

                        }




                        else
                        {

                            Toast.makeText(getApplicationContext(), "wrong", Toast.LENGTH_SHORT).show();
                        }


                    }
                });*/


                /*Map<String, Object> data1 = new HashMap<>();
                data1.put("name", "San Francisco");
                data1.put("state", "CA");
                data1.put("country", "USA");
                data1.put("capital", false);
                data1.put("population", 860000);
                cities.document("SF").set(data1);

                Map<String, Object> data2 = new HashMap<>();
                data2.put("name", "Los Angeles");
                data2.put("state", "CA");
                data2.put("country", "USA");
                data2.put("capital", false);
                data2.put("population", 3900000);
                cities.document("LA").set(data2);

                Map<String, Object> data3 = new HashMap<>();
                data3.put("name", "Washington D.C.");
                data3.put("state", null);
                data3.put("country", "USA");
                data3.put("capital", true);
                data3.put("population", 680000);
                cities.document("DC").set(data3);

                Map<String, Object> data4 = new HashMap<>();
                data4.put("name", "Tokyo");
                data4.put("state", null);
                data4.put("country", "Japan");
                data4.put("capital", true);
                data4.put("population", 9000000);
                cities.document("TOK").set(data4);

                Map<String, Object> data5 = new HashMap<>();
                data5.put("name", "Beijing");
                data5.put("state", null);
                data5.put("country", "China");
                data5.put("capital", true);
                data5.put("population", 21500000);
                cities.document("BJ").set(data5);*/




            // manually set the Document title
                //db.collection("cities").document("VI").set(users);
//automatically set an ID


             /*   // Create a new user with a first and last name
                Map<String, Object> user = new HashMap<>();
                user.put(USERNAME_KEY, "Ada");
                user.put(PASSWORD_KEY, "Lovelace");
                user.put(NUMBER_KEY, 1815);

            // Add a new document with a generated ID
                db.collection(COLLECTION_NAME_KEY)
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("HAI", "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("HAI", "Error adding document", e);
                            }
                        });*/
            }
        });

/*
        bt_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                DocumentReference docRef = db.collection("cities").document();

                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {


                        Users city = documentSnapshot.toObject(Users.class);

                        Log.d("HAI", city.getName().toString()+ " => " +city.getCountry().toString());

                    }
                });



              */
/*  db.collection("cities")

                        .get()

                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {





                                if (task.isSuccessful()) {





                                  //                                         for (DocumentSnapshot document : task.getResult()) {





                                   *//*
*/
/*     for (Map.Entry<String, Object> entry : document.getData().entrySet()) {

                                           Log.d("HAI", entry.getKey() + " => " +entry.getValue());



                                            Users c =new Users();

                                            c.setName(entry.getValue().toString());
                                            c.setCountry(entry.getValue().toString());
                                            c.setState(entry.getValue().toString());

                                            Users_LIST.add(c);


                                        }

                                         Log.w("HAI", String.valueOf(Users_LIST.size()));
                                        Log.w("HAI", String.valueOf(Users_LIST));
                                       // Log.d("HAI", entry.getKey() + " => " +entry.getValue());

                                        Users city = new Users();

                                        //Log.d("HAI", document.getId() + " => " + document.getData());

                                        tv_details.setText(document.getData().toString());*//*
*/
/*
                                   // }
                                } else {
                                    Log.w("HAI", "Error getting documents.", task.getException());
                                }
                            }
                        });*//*


            }
        });
*/


















    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
