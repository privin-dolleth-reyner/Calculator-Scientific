package com.calculator_sci.privin;

import java.util.Iterator;

import android.os.Bundle;
import android.widget.TextView;

public class show extends CalculatorActivity {
	
		private TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,t20;
		

		@Override
		public void onCreate(Bundle savedInstanceState){
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.show);
			//t=(TextView) findViewById(R.id.text1);
			in();
			CalculatorActivity m= new CalculatorActivity();
			//t3.setText("working");
			String[] e={},result={};
//			Iterator it=al.iterator();
//			Iterator re=r.iterator();
			
//			for(int j=0;it.hasNext();j++)
//			{
//				e[j]=String.valueOf(it.next());
//				result[j]=String.valueOf(re.next());
//			}
//			try{
//
//				t1.setText(e[0]);
//				t2.setText(result[0]);
//
//			}catch(Exception er)
//			{
//				t1.setText("error");
//				t2.setText("error");
//			}
			//t[i]=(TextView) findViewById(R.id.abttxt);
		//	t.setText("working");
			
			//addPreferencesFromResource(R.xml.show);
		

	}
		public void in()
		{
			t1=(TextView) findViewById(R.id.text1);
			t2=(TextView) findViewById(R.id.text2);
			t3=(TextView) findViewById(R.id.text3);
			t4=(TextView) findViewById(R.id.text4);
			t5=(TextView) findViewById(R.id.text5);
			t6=(TextView) findViewById(R.id.text6);
			t7=(TextView) findViewById(R.id.text7);
			t8=(TextView) findViewById(R.id.text8);
			t9=(TextView) findViewById(R.id.text9);
			t10=(TextView) findViewById(R.id.text10);
			t11=(TextView) findViewById(R.id.text11);
			t12=(TextView) findViewById(R.id.text12);
			t13=(TextView) findViewById(R.id.text13);
			t14=(TextView) findViewById(R.id.text14);
			t15=(TextView) findViewById(R.id.text15);
			t16=(TextView) findViewById(R.id.text16);
			t17=(TextView) findViewById(R.id.text17);
			t18=(TextView) findViewById(R.id.text18);
			t19=(TextView) findViewById(R.id.text19);
			t20=(TextView) findViewById(R.id.text20);
		}
}
