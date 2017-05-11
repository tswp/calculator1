package com.example.calculator;

import android.os.Bundle;
import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener{

	//初始化计算器按钮
	Button btn_0;
	Button btn_1;
	Button btn_2;
	Button btn_3;
	Button btn_4;
	Button btn_5;
	Button btn_6;
	Button btn_7;
	Button btn_8;
	Button btn_9;
	Button btn_point;
	Button btn_clear;
	Button btn_del;
	Button btn_plus;
	Button btn_minus;		//减号按钮
	Button btn_multiply;	//乘号按钮
	Button btn_divide;		//除号按钮
	Button btn_equle;		//等于

	EditText et_input;		//显示框

	boolean clear_flag;		//清空标识！用于：当已经运算出结果的时候，我们再次点击数字按钮的时候，清空屏幕的作用！

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//实例化各按钮、控件
		btn_0=(Button) findViewById(R.id.btn_0);
		btn_1=(Button) findViewById(R.id.btn_1);
		btn_2=(Button) findViewById(R.id.btn_2);
		btn_3=(Button) findViewById(R.id.btn_3);
		btn_4=(Button) findViewById(R.id.btn_4);
		btn_5=(Button) findViewById(R.id.btn_5);
		btn_6=(Button) findViewById(R.id.btn_6);
		btn_7=(Button) findViewById(R.id.btn_7);
		btn_8=(Button) findViewById(R.id.btn_8);
		btn_9=(Button) findViewById(R.id.btn_9);
		btn_point=(Button) findViewById(R.id.btn_point);
		btn_clear=(Button) findViewById(R.id.btn_clear);
		btn_del=(Button) findViewById(R.id.btn_del);
		btn_plus=(Button) findViewById(R.id.btn_plus);
		btn_minus=(Button) findViewById(R.id.btn_minus);
		btn_multiply=(Button) findViewById(R.id.btn_multiply);
		btn_divide=(Button) findViewById(R.id.btn_divide);
		btn_equle=(Button) findViewById(R.id.btn_equal);

		et_input=(EditText) findViewById(R.id.edittext);

		//实现各个按钮的监听事件
		btn_0.setOnClickListener(this);
		btn_1.setOnClickListener(this);
		btn_2.setOnClickListener(this);
		btn_3.setOnClickListener(this);
		btn_4.setOnClickListener(this);
		btn_5.setOnClickListener(this);
		btn_6.setOnClickListener(this);
		btn_7.setOnClickListener(this);
		btn_8.setOnClickListener(this);
		btn_9.setOnClickListener(this);
		btn_point.setOnClickListener(this);
		btn_clear.setOnClickListener(this);
		btn_del.setOnClickListener(this);
		btn_plus.setOnClickListener(this);
		btn_minus.setOnClickListener(this);
		btn_multiply.setOnClickListener(this);
		btn_divide.setOnClickListener(this);
		btn_equle.setOnClickListener(this);
	}

	/**
	 * 在onClick中判断是点的哪个按钮
	 */
	@Override
	public void onClick(View view) {

		// 显示屏幕内容
		String str=et_input.getText().toString();

		/**
		 * 数字按钮
		 */
		switch (view.getId()) {
			case R.id.btn_0:
			case R.id.btn_1:
			case R.id.btn_2:
			case R.id.btn_3:
			case R.id.btn_4:
			case R.id.btn_5:
			case R.id.btn_6:
			case R.id.btn_7:
			case R.id.btn_8:
			case R.id.btn_9:
			case R.id.btn_point:
				if (clear_flag) {
					clear_flag=false;
					str="";
					et_input.setText("");
				}
				et_input.setText(str+((Button)view).getText());
				break;
			/**
			 * 运算符
			 */
			case R.id.btn_plus:
			case R.id.btn_minus:
			case R.id.btn_multiply:
			case R.id.btn_divide:
				if (clear_flag) {
					clear_flag=false;
					str="";
					et_input.setText("");
				}
				et_input.setText(str+" "+((Button)view).getText()+" ");
				break;
			/**
			 * 清除按钮
			 */
			case R.id.btn_clear:
				clear_flag=false;
				str="";
				et_input.setText("");
				break;

			/**
			 * 删除按钮(一个字符一个字符删除）
			 */
			case R.id.btn_del:
				if (clear_flag) {
					clear_flag=false;
					str="";
					et_input.setText("");
				}else if (str!=null&&!str.equals("")) {
					et_input.setText(str.substring(0, str.length()-1));
					//字符串内容减一
				}
				break;
			/**
			 * 等于按钮
			 */
			case R.id.btn_equal:
				getResult();
				break;
			default:
				break;
		}
	}

	/**
	 * 逻辑运算函数
	 */

	private void getResult(){

		String exp=et_input.getText().toString();		//获取屏幕内容

		if(exp==null||exp.equals("")){		//如果为空,直接return
			return;
		}
		if(!exp.contains("")){		//没有运算符，不用运算（在前面我们在运算符前后加了空格），直接return
			return;
		}

		if (clear_flag) {		//防止点完等号过后再次点击等号，所以需要将进行判断，同理：在点击其他符号的时候也要
			clear_flag=false;
			return;
		}
		clear_flag=true;		//当我点击等号的时候，清空标识为真
		double result =0;

		String s1=exp.substring(0,exp.indexOf(" "));  //获取运算符前面的数字
		String op=exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);	//获取运算符
		String s2=exp.substring(exp.indexOf(" ")+3);	//获取运算符后面的数字

		/**
		 * 对后面的s1 ,op ,s2进行逻辑判断，即能否满足运算条件
		 */

		if (!s1.equals("")&&!s2.equals("")) {		//s1 s2都不是空
			double d1=Double.parseDouble(s1);
			double d2=Double.parseDouble(s2);

			if (op.equals("+")) {
				result=d1+d2;
			}else  if (op.equals("-")){
				result=d1-d2;
			}else if (op.equals("×")){
				result=d1*d2;
			}else if (op.equals("÷")){
				if (d2==0) {
					result=0;
				}
				else {
					result=d1/d2;
				}
			}
			/**
			 * 需要对运算值进行判断，是否需要使用int类型的
			 */
			if (!s1.contains(".")&&!s2.contains(".")&&!op.equals("÷")) {
				int r=(int)result;
				et_input.setText(r+"");
			}else{
				et_input.setText(result+"");
			}
		}else if(!s1.equals("")&&s2.equals("")){		//前面有数字，后面没有数字
			et_input.setText(exp);
		}else if(s1.equals("")&&!s2.equals("")){		//后面有数字，前面没有数字
			double d2=Double.parseDouble(s2);
			if (op.equals("+")) {
				result=0+d2;
			}else  if (op.equals("-")){
				result=0-d2;
			}else if (op.equals("×")){
				result=0;
			}else if (op.equals("÷")){
				result=0;
			}
			if (!s1.contains(".")&&!s2.contains(".")) {
				int r=(int)result;
				et_input.setText(r+"");
			}else{
				et_input.setText(result+"");
			}
		}else {		//前后都为空
			et_input.setText("");
		}

	}

}
	


