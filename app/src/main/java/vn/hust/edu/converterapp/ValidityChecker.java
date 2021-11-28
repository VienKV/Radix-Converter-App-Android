package vn.hust.edu.converterapp;

import android.content.Context;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ValidityChecker {
    String digit;
    boolean check=false;
    //TextView fromSystem,toSystem;
    //Spinner fromSpinner, toSpinner;
    //String from,to;
    String fromS,toS;
    String errorMsg="Please enter correct number!";
    /*ValidityChecker(String s, TextView fromSystem, TextView toSystem) {
        digit=s;
        this.fromSystem=fromSystem;
        from=fromSystem.getText().toString();
        to=toSystem.getText().toString();
        this.toSystem=toSystem;
        validityCheck();
    }*/

    ValidityChecker(String s, String from, String to) {
        digit = s;
        fromS = from;
        toS = to;
        validityCheck();
    }

    public void validityCheck()
    {
        switch (fromS) {
            case "Binary":
                Binary();
                break;
            case "Decimal":
                Decimal();
                break;
            case "Octal":
                Octal();
                break;
            default:
                check = true;
                break;
        }
    }

    public void Binary()
    {
        if(!(digit.equals("1") || digit.equals("0")))
        {
            errorMsg="Binary numbers cannot contain the digit "+digit;
        }
        else
        {
            check=true;
        }
    }

    public void Decimal()
    {
        if((digit.equals("A") || digit.equals("B") || digit.equals("C") || digit.equals("D") || digit.equals("E") || digit.equals("F")))
        {
            errorMsg="Decimal numbers cannot contain the digit "+digit;
        }
        else
        {
            check=true;
        }
    }

    public void Octal()
    {
        if((digit.equals("A") || digit.equals("B") || digit.equals("C") || digit.equals("D") || digit.equals("E") || digit.equals("F") || digit.equals("9") || digit.equals("8")))
        {
            errorMsg="Octal numbers cannot contain the digit "+digit;
        }
        else
        {
            check=true;
        }
    }






    public void makeToast(Context vContext){
        Toast.makeText(vContext, errorMsg, Toast.LENGTH_SHORT).show();
    }



    public boolean checked()
    {
        return check;
    }


}
