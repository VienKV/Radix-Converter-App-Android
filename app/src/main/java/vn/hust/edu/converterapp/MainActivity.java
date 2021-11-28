package vn.hust.edu.converterapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Vibrator;

import java.util.ArrayList;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    int len;
    Button convert;
    private Vibrator myVib;
    TextView number, resultText;//fromSystem, toSystem;
    Spinner fromSpinner, toSpinner;
    String from = "", to = "";
    protected String numberValue = "", currentDigit = "", result = "";
    //String[] listItems = {"Binary", "Decimal", "Octal", "Hexadecimal" };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //fromSystem = findViewById(R.id.fromSystem);
        //toSystem = findViewById(R.id.toSystem);
        //fromSystem.setText(listItems[from]);
        //toSystem.setText(listItems[to]);

        Spinner fromSpinner = (Spinner) findViewById(R.id.fromSpinner);

        final ArrayList<String> listItem1 = new ArrayList<String>();
        listItem1.add("Binary");
        listItem1.add("Decimal");
        listItem1.add("Octal");
        listItem1.add("Hexadecimal");

        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listItem1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromSpinner.setAdapter(adapter1);
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                from = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Converted from " + from, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner toSpinner = (Spinner) findViewById(R.id.toSpinner);

        final ArrayList<String> listItem2 = new ArrayList<String>();
        listItem2.add("Binary");
        listItem2.add("Decimal");
        listItem2.add("Octal");
        listItem2.add("Hexadecimal");

        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listItem2);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpinner.setAdapter(adapter2);
        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                to = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Convert to " + to, Toast.LENGTH_LONG).show();
                //toSpinner.setOnItemSelectedListener(toSpinner.getOnItemSelectedListener());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        number = findViewById(R.id.number);
        resultText = findViewById(R.id.result);
        number.setFocusable(true);
        number.setEnabled(false);
        number.setCursorVisible(false);

        myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        convert = this.findViewById(R.id.convert);


    }
    /*------------------------------------------------Converter------------------------------------------*/
    public void convert(View view) {
        //myVib.vibrate(50);
        /*String fromSysString = fromSystem.getText().toString();
        String toSysString = toSystem.getText().toString();*/

        Log.i("num", String.valueOf(numberValue));
        try {

            //Conversion c = new Conversion(numberValue, fromSysString, toSysString);
            Conversion c = new Conversion(numberValue, from, to);
            result = c.getN();
            if (!result.equals("")) {
                resultText.setText(result);
                Log.i("", result);
            } else {
                resultText.setText("");
                Toast.makeText(this, "Error! Please check your input!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultText.setText("");
            Toast.makeText(this, "Error! Please check your input!", Toast.LENGTH_SHORT).show();
        }
    }


    /*------------------------------------------------Get the Digit------------------------------------------*/
    public void getDigit(View view) {
        //myVib.vibrate(20);
        len = numberValue.length();
        number = findViewById(R.id.number);
        if (len <= 14) {
            GetNumber getNumber = new GetNumber(view);
            String digit = getNumber.getD();
            currentDigit = digit;
            ValidityChecker validityChecker = new ValidityChecker(digit, from, to);
            if (validityChecker.checked()) {
                numberValue = numberValue + digit;
                number.setText(numberValue);
            } else {
                validityChecker.makeToast(this);
            }
        } else {
            Toast.makeText(this, "Insertion limited to 14 digit!", Toast.LENGTH_SHORT).show();
        }
    }







    /*------------------------------------------------Delete Button action------------------------------------------*/

    public void deleteDigit(View view) {
        //myVib.vibrate(20);
        try {
            len = numberValue.length() - 1;


            /*if (".".equals(String.valueOf(numberValue.charAt(len)))) {
                flag = 0;
            }*/


            if (len >= 0) {
                numberValue = numberValue.substring(0, len);
                number.setText(numberValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /*------------------------------------------------Clear Button action------------------------------------------*/

    public void clear(View view) {
        //myVib.vibrate(20);
        numberValue = "";
        number.setText(numberValue);
    }


    /*------------------------------------------------fromSystem------------------------------------------*/

    /*public void fromSys(View view) {
        //myVib.vibrate(30);
        //numberValue="";
        from = (from % 4) + 1;
        //number.setText(numberValue);
        fromSystem = findViewById(R.id.fromSystem);
        fromSystem.setText(listItems[from % 4]);
        //from=(from%4)+1;
        //Log.i("from",String.valueOf(from));

    }*/



    /*------------------------------------------------toSystem------------------------------------------*/

   /* public void toSys(View view) {
        //myVib.vibrate(30);
        toSystem = findViewById(R.id.toSystem);
        toSystem.setText(listItems[to % 4]);
        to = (to % 4) + 1;
        //Log.i("to",String.valueOf(to));
    }
*/

    public void copyToClipboard2(View view) {

        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", result);
        Toast.makeText(this, "Result copied to Clipboard", Toast.LENGTH_SHORT).show();
        clipboard.setPrimaryClip(clip);
    }

    public void copyToClipboard1(View view) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        try {
            CharSequence textToPaste = Objects.requireNonNull(clipboard.getPrimaryClip()).getItemAt(0).getText();
            number.setText(textToPaste);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Nothing to paste!", Toast.LENGTH_SHORT).show();
        }


       /*android.content.ClipboardManager clipboard = (android.content.ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", numberValue);
        Toast.makeText(this, "Input number copied to clipboard", Toast.LENGTH_SHORT).show();
        clipboard.setPrimaryClip(clip);*/
    }
}

