package com.calculator_sci.privin;
import com.calculator_sci.privin.model.Calculator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CalculatorActivity extends Activity implements View.OnClickListener, Calculator.CalculatorViewDelegate {

    private static final String TAG = "CalculatorActivity";
    TextView display1,display2;
    private Vibrator myVib;
    private Calculator model = new Calculator();


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        myVib=(Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        setContentView(R.layout.pro);
        initialize();
        model.setDelegate(this);


    }

    private void initialize(){
        Button[] buttons = new Button[]{(Button) findViewById(R.id.one),
                (Button) findViewById(R.id.two),
                (Button) findViewById(R.id.three),
                (Button) findViewById(R.id.four),
                (Button) findViewById(R.id.five),
                (Button) findViewById(R.id.six),
                (Button) findViewById(R.id.seven),
                (Button) findViewById(R.id.eight),
                (Button) findViewById(R.id.nine),
                (Button) findViewById(R.id.zero),
                (Button) findViewById(R.id.addition),
                (Button) findViewById(R.id.subtraction),
                (Button) findViewById(R.id.divide),
                (Button) findViewById(R.id.muliply),
                (Button) findViewById(R.id.c),
                (Button) findViewById(R.id.ans),
                (Button) findViewById(R.id.dot),
                (Button) findViewById(R.id.sqrt),
                (Button) findViewById(R.id.per),
                (Button) findViewById(R.id.pow),
                (Button) findViewById(R.id.ac),
                (Button) findViewById(R.id.neg),
                (Button) findViewById(R.id.reci),
                (Button) findViewById(R.id.del),
                (Button) findViewById(R.id.sin),
                (Button) findViewById(R.id.cos),
                (Button) findViewById(R.id.tan),
                (Button) findViewById(R.id.pi),
                (Button) findViewById(R.id.ms),
                (Button) findViewById(R.id.mn),
                (Button) findViewById(R.id.mc),
                (Button) findViewById(R.id.mp),
                (Button) findViewById(R.id.x2),
                (Button) findViewById(R.id.ln),
                (Button) findViewById(R.id.log),
                (Button) findViewById(R.id.crt)
        };
        for (Button b : buttons){
            b.setOnClickListener(this);
            b.setHapticFeedbackEnabled(true);
            b.setSoundEffectsEnabled(true);
        }
        display1=(TextView) findViewById(R.id.display1);
        display2=(TextView) findViewById(R.id.display2);
        display1.setGravity(Gravity.START);
        display2.setGravity(Gravity.END);
    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // TODO Auto-generated method stub
        super.onCreateOptionsMenu(menu);
        MenuInflater asd=getMenuInflater();
        asd.inflate(R.menu.main, menu);
        return true;

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch(item.getItemId()){
            case R.id.settings:
                Intent i=new Intent("com.privin.calculator.setting");
                startActivity(i);
                break;
            case R.id.exit:
                finish();
                break;
        }
        return false;
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        SharedPreferences getpref=PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean vb=getpref.getBoolean("v", true);
        if(vb)
        {
            myVib.vibrate(50);
        }
        v.playSoundEffect(SoundEffectConstants.CLICK);
        Button button = (Button) findViewById(v.getId());
        switch(v.getTag().toString()){
            case "operand":
                model.checkUnCalculatedResult();
                model.setOperand((button.getText().toString()));
                Log.d("Cal","in operand");
                model.updateView();
//                model.result(false);
                break;
            case "operator":
                model.setOp(button.getText().toString());
                break;
            case "equalTo":
                model.result(true);
                break;
            case "del":
                model.delete();
                break;
            case "clearMemory":
                model.clearMemory();
                break;
            case "clear":
                model.clear();
                break;
            case "memory":
                model.memory(button.getText().toString());
                break;

            default: //Do Nothing
        }


    }

    @Override
    public void updateView() {
        display2.setText(model.getExpression());
        display1.setText(model.getFullExpression());
    }
}