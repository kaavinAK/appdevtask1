package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = getIntent();
        if(i!=null)
        {
            try {
                render();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else
        {
            try {
                render();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


    }

    public static String date(String date) throws ParseException {
        String[] everything = date.split("/");
        Log.d("dates", "date: "+date);
        String month = everything[1];
        String day = everything[0];
        String year = everything[2];
        String inputDateStr = String.format("%s/%s/%s", day, month, year);
        Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputDate);
        String dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US).toUpperCase();
        return dayOfWeek;
    }
   public void answer(View v) throws ParseException {
       TextView score = findViewById(R.id.score);
       String initialscore = score.getText().toString();
        TextView dateview = findViewById(R.id.date);
        String date = dateview.getText().toString();
        Button buttonday = (Button) v;
        String day = buttonday.getText().toString().toUpperCase();
        String ans = date(date);
       Log.d("the answer", "answer: "+ans);

       if(day.equals(ans))
       {
           int scores = Integer.parseInt(initialscore);
           scores+=1;
           Log.d("answer is correct", "answer: "+score);
           score.setText(Integer.toString(scores));
           render();
       }
       else{
Intent i = new Intent(this,settings.class);
i.putExtra("score",initialscore);
i.putExtra("day",ans);
startActivity(i);
       }
   }
    public  String[] remove (String[] array,int index)
    {
         String[] newarray = new String[array.length-1];
         int j=0;
         for(int i=0;i< array.length;i++)
         {
             if(j>=0 && j< array.length-1)
             {
                 if(i!=index)
                 {
                     newarray[j]=array[i];
                     Log.d("remove", "remove: "+newarray[j]);
                     j++;
                 }

             }

         }
         return newarray;
    }
   public void render() throws ParseException {
       String[] days = {"monday","tuesday","wednesday","thursday","friday","saturday","sunday"};

       Random rd = new Random();
       int month = Math.abs((rd.nextInt())%12)+1;
       int year = Math.abs(rd.nextInt()%90)+1950;
       int day=0;
       if(month==2)
       {
           day = Math.abs((rd.nextInt())%28)+1;
       }
       if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 ||month==12 )
       {
           day=Math.abs((rd.nextInt())%31)+1;
       }
       else{
           day=Math.abs((rd.nextInt())%30)+1;
       }
       String date;
       if(day<10)
       {
          date= '0'+Integer.toString(day)+'/'+Integer.toString(month)+'/'+Integer.toString(year);
       }
       if(month<10)
       {
           date=Integer.toString(day)+'/'+'0'+Integer.toString(month)+'/'+Integer.toString(year);
       }
       else
       {
          date= Integer.toString(day)+'/'+Integer.toString(month)+'/'+Integer.toString(year);
       }

       TextView text = findViewById(R.id.date);
       text.setText(date);
       String[] dates=new String[4];
       Button[] buttons = new Button[4];
       int based=7;
       for(int i=0;i<4;i++)
       {
           int random=Math.abs(rd.nextInt()%based);
           dates[i]=days[random];
           days=remove(days,random);
           Log.d("days", "render: "+dates[i]);
           Log.d("i", "render: "+random);
           based=based-1;
       }
       Button b1 = findViewById(R.id.option1);
       buttons[0]=b1;
       Button b2 = findViewById(R.id.option2);
       buttons[1]=b2;
       Button b3 = findViewById(R.id.option3);
       buttons [2] =b3;
       Button b4 = findViewById(R.id.option4);
       buttons[3]=b4;
       Log.d("days", "render: "+days);
       TextView dateview = findViewById(R.id.date);
       String dateinview = dateview.getText().toString();



       for(int i=0;i<4;i++)
       {
           buttons[i].setText(dates[i]);
       }
       String ans = date(dateinview);
       int answerrand = Math.abs(rd.nextInt()%4);
       int spec=0;
      // Log.d("before before spec", "render: "+"come on");
       Log.d("day", "render: "+ans);
       for(int i=0;i<4;i++)
       {
           Log.d("before spec", "render: "+buttons[i].getText());
           if(!buttons[i].getText().toString().toUpperCase().equals(ans))
           {
                spec++;
               Log.d("spec", "render: "+spec);
           }

       }
       if(spec==4)
       {
           buttons[answerrand].setText(ans);
       }

   }



}