package com.octoriz.abids.saarc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.octoriz.abids.saarc.Entity.Name;
import com.octoriz.abids.saarc.Model.People;
import com.octoriz.abids.saarc.Preference.Preference;
import com.octoriz.abids.saarc.ViewModel.ShakeViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShakeActivity extends AppCompatActivity {

    private SensorManager mSensorManager;

    private ShakeEventListener mSensorListener;
    private TextView txtList,shake;
    private ShakeViewModel shakeViewModel;
    private List<People> allNames = new ArrayList<>();
    private List<People> sortedNames = new ArrayList<>();
    private int totalPeople;
    private String value="habijabi";
    private int t;
    private int comb;
    private int counter = 0;
    private int check = 0;
    private List<String> xSplit = new ArrayList<>();
    private List<String> ySplit = new ArrayList<>();
    private ArrayList<String> roomList = new ArrayList<>();
    private int combinations = -1;
    private int changer;
    private ArrayList<String> sharedPrefRoomList;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorListener = new ShakeEventListener();
        txtList = findViewById(R.id.list);
        shake = findViewById(R.id.shake);
        shakeViewModel = ViewModelProviders.of(this).get(ShakeViewModel.class);

        shakeViewModel.getAllNames().observe(this, new Observer<List<Name>>() {
            @Override
            public void onChanged(List<Name> names) {
                totalPeople = names.size();
                t = totalPeople/2;
                comb = t;
                txtList.append("Total People "+String.valueOf(totalPeople)+"\n");
                int a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0, o=0, p=0, q=0, r=0, s=0, t=0;
                List<String> country1 = new ArrayList<>();
                List<String> country2 = new ArrayList<>();
                List<String> country3 = new ArrayList<>();
                List<String> country4 = new ArrayList<>();
                List<String> country5 = new ArrayList<>();
                List<String> country6 = new ArrayList<>();
                List<String> country7 = new ArrayList<>();
                List<String> country8 = new ArrayList<>();
                List<String> country9 = new ArrayList<>();
                List<String> country10 = new ArrayList<>();
                List<String> country11 = new ArrayList<>();
                List<String> country12 = new ArrayList<>();
                List<String> country13 = new ArrayList<>();
                List<String> country14 = new ArrayList<>();

                for(int i=0; i<names.size(); i++){

                    if(names.get(i).getCountry_id()==1){
                        a++;
                        country1.add(names.get(i).getName());
                    }
                    else if(names.get(i).getCountry_id()==2){
                        b++;
                        country2.add(names.get(i).getName());
                    }
                    else if(names.get(i).getCountry_id()==3){
                        c++;
                        country3.add(names.get(i).getName());
                    }
                    else if(names.get(i).getCountry_id()==4){
                        d++;
                        country4.add(names.get(i).getName());
                    }
                    else if(names.get(i).getCountry_id()==5){
                        e++;
                        country5.add(names.get(i).getName());
                    }
                    else if(names.get(i).getCountry_id()==6){
                        f++;
                        country6.add(names.get(i).getName());
                    }
                    else if(names.get(i).getCountry_id()==7){
                        g++;
                        country7.add(names.get(i).getName());
                    }
                    else if(names.get(i).getCountry_id()==8){
                        h++;
                        country8.add(names.get(i).getName());
                    }
                    else if(names.get(i).getCountry_id()==9){
                        o++;
                        country9.add(names.get(i).getName());
                    }
                    else if(names.get(i).getCountry_id()==10){
                        p++;
                        country10.add(names.get(i).getName());
                    }
                    else if(names.get(i).getCountry_id()==11){
                        q++;
                        country11.add(names.get(i).getName());
                    }
                    else if(names.get(i).getCountry_id()==12){
                        r++;
                        country12.add(names.get(i).getName());
                    }
                    else if(names.get(i).getCountry_id()==13){
                        s++;
                        country13.add(names.get(i).getName());
                    }
                    else if(names.get(i).getCountry_id()==14){
                        t++;
                        country14.add(names.get(i).getName());
                    }
                }
                allNames.add(new People(a,country1));
                allNames.add(new People(b,country2));
                allNames.add(new People(c,country3));
                allNames.add(new People(d,country4));
                allNames.add(new People(e,country5));
                allNames.add(new People(f,country6));
                allNames.add(new People(g,country7));
                allNames.add(new People(h,country8));
                allNames.add(new People(o,country9));
                allNames.add(new People(p,country10));
                allNames.add(new People(q,country11));
                allNames.add(new People(r,country12));
                allNames.add(new People(s,country13));
                allNames.add(new People(t,country14));


                Collections.sort(allNames, new SortByNumberOfPeople());
                //printList(allNames);
            }
        });

        sharedPrefRoomList = new ArrayList<>();
        try{
            sharedPrefRoomList = Preference.getArrayPrefs("RoomList",ShakeActivity.this);
        value = Preference.getPrefs("value",ShakeActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
//
        mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {

            public void onShake() {
                Toast.makeText(ShakeActivity.this, "Shake Detected!", Toast.LENGTH_SHORT).show();
            if(allNames.size()!=0){
                if(combinations==-1 && !value.equals("info")){
                    String names;
                    combinations = doRoomAllocating(allNames);

                    shake.setText("Shake Again for more combinations");
                }else if(combinations>1 && !value.equals("info")){
                    txtList.setText("");
                    doRoomMoreAllocating();
                }
                else{
                    txtList.setText("");
                    if(roomList.size()!=0){
                        Preference.setArrayPrefs("RoomList",roomList,ShakeActivity.this);
                        Preference.setPrefs("value","info",ShakeActivity.this);

                    }
                    if(sharedPrefRoomList.size()>0){
                        for(int i=0; i<sharedPrefRoomList.size(); i++){
                            txtList.append("****possible room combinations*****\n");
                            txtList.append(sharedPrefRoomList.get(i)+"\n");
                        }
                        shake.setText("All combinations!");

                    }else{
                        shake.setText("No more combinations!");
                    }
                }
            }else{
                Toast.makeText(ShakeActivity.this, "List is empty", Toast.LENGTH_SHORT).show();
            }

            }
        });

    }

    private void doRoomMoreAllocating() {
        String temp;
        temp = ySplit.get(ySplit.size()-1);
        for(int i=ySplit.size()-1; i>0; i--){
            ySplit.set(i,ySplit.get(i-1));
        }
        ySplit.set(0,temp);

        String names = "";
        for(int i=0; i<t; i++){
            int roomNo = i+1;

            if((totalPeople%2 == 1) && i==changer){
                txtList.append("Room No "+roomNo+". "+xSplit.get(i)+"  AND  "+ySplit.get(i)+"  AND  "+ySplit.get(t)+"\n");
                names = names + "Room No "+roomNo+". "+xSplit.get(i)+"  AND  "+ySplit.get(i)+"  AND  "+ySplit.get(t)+"\n";

            }else{
                txtList.append("Room No "+roomNo+". "+xSplit.get(i)+"  AND  "+ySplit.get(i)+"\n");
                names = names + "Room No "+roomNo+". "+xSplit.get(i)+"  AND  "+ySplit.get(i)+"\n";

            }
        }
        roomList.add(names);
        ++changer;
        combinations--;
    }

    private int doRoomAllocating(List<People> allNames) {
        for(int i=0; i<14; i++){
            int possible = allNames.get(i).getPeopleCount();
            for(int j=0; j<allNames.get(i).getPeopleCount(); j++){
                if(counter!=t){
                    xSplit.add(allNames.get(i).getPeopleNames().get(j));
                    ++counter;
                    --possible;
                    if(possible == 0){
                        check = 1;
                    }
                }
                else{
                    if(possible!=0 && check == 0 && possible!= allNames.get(i).getPeopleCount()){
//                        comb = t - (possible*2)-1;
                        //comb = t - possible;
                        comb = t - allNames.get(i).getPeopleCount()+1;
                        possible = 0;
                        check = 1;
                    }
                    ySplit.add(allNames.get(i).getPeopleNames().get(j));
                }
                check = 0;
            }
        }

        changer = 0;
        txtList.append("Room Allocation\n");
        String names = "";
        for(int i=0; i<t; i++){
            int roomNo = i+1;
            if(((totalPeople%2) == 1) && (i == changer)){
                txtList.append("Room No "+roomNo+". "+xSplit.get(i)+"  AND  "+ySplit.get(i)+"  AND  "+ySplit.get(t)+"\n");
                names = names + "Room No "+roomNo+". "+xSplit.get(i)+"  AND  "+ySplit.get(i)+"  AND  "+ySplit.get(t)+"\n";
            }else{
                txtList.append("Room No "+roomNo+". "+xSplit.get(i)+"  AND  "+ySplit.get(i)+"\n");
                names = names + "Room No "+roomNo+". "+xSplit.get(i)+"  AND  "+ySplit.get(i)+"\n";
            }
            txtList.append("\n");
        }

        roomList.add(names);
        ++changer;
//        txtList.append(comb+" Combination Possible");
        return comb;
    }

    private void printList(List<People> allNames) {
        for(int j=0; j<allNames.size(); j++){
            txtList.append(allNames.get(j).getPeopleCount()+"\n");
            for(int i=0; i<allNames.get(j).getPeopleNames().size(); i++){
                txtList.append(allNames.get(j).getPeopleNames().get(i)+"\n");
            }
        }
    }

    class SortByNumberOfPeople implements Comparator<People>
    {
        // Used for sorting in ascending order of
        // roll number
        public int compare(People a, People b)
        {
            return b.getPeopleCount() - a.getPeopleCount();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }

}
