package com.example.sharedpreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText nameText;
    private EditText numText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameText = (EditText)this.findViewById(R.id.et1);
        numText = (EditText)this.findViewById(R.id.et2);
        Button button = (Button)this.findViewById(R.id.bt1);
        Button showButton = (Button)this.findViewById(R.id.bt2);
        button.setOnClickListener(listener);
        showButton.setOnClickListener(listener);

        // 回显
        SharedPreferences sharedPreferences=getSharedPreferences("李心宇",
                Activity.MODE_PRIVATE);
        String nameValue = sharedPreferences.getString("name", "");
        int numValue = sharedPreferences.getInt("num",2018010223);
        nameText.setText(nameValue);
        numText.setText(String.valueOf(numValue));
    }

    private View.OnClickListener listener = new View.OnClickListener(){
        public void onClick(View v) {
            Button button = (Button)v;
            //ljq123文件存放在/data/data/<package name>/shared_prefs目录下
            SharedPreferences sharedPreferences=getSharedPreferences("李心宇",
                    Activity.MODE_PRIVATE );
            switch (button.getId()) {
                case R.id.bt1:
                    String name = nameText.getText().toString();
                    int num = Integer.parseInt(numText.getText().toString());
                    Editor editor = sharedPreferences.edit(); //获取编辑器
                    editor.putString("name","李心宇");
                    editor.putInt("num", num);
                    editor.commit();//提交修改
                    Toast.makeText( MainActivity.this, "保存成功", Toast.LENGTH_LONG).show();
                    break;
                case R.id.bt2:
                    String nameValue = sharedPreferences.getString("name", "");
                    int numValue = sharedPreferences.getInt("num", 1);
                    Toast.makeText(MainActivity.this,"姓名："+nameValue + "，学号：" + numValue,Toast.LENGTH_LONG).show();

                    break;
            }
        }
    };
}
