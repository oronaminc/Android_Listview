package org.techtown.exercise_inflation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SingerAdapter adapter;

    EditText editText;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);

        ListView listView = (ListView)findViewById(R.id.listView);

        adapter = new SingerAdapter();
        adapter.addItem(new SingerItem("선물가게", "02-645-9975", R.drawable.logo1));
        adapter.addItem(new SingerItem("쿠키상점", "02-978-4421", R.drawable.logo2));
        adapter.addItem(new SingerItem("폰대리점", "02-447-0134", R.drawable.logo3));
        adapter.addItem(new SingerItem("푸드코트", "02-900-0315", R.drawable.logo4));

        //어댑터를 적용하는 구간
        listView.setAdapter(adapter);

        //어뎁터의 객체를 클릭 했을 때 나오는 기능 구형
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SingerItem item = (SingerItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "선택 : " + item.getName(), Toast.LENGTH_LONG).show();
            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String name = editText.getText().toString();
                String mobile = editText2.getText().toString();

                adapter.addItem(new SingerItem(name, mobile, R.drawable.logo4));
                adapter.notifyDataSetChanged();
            }
        });



    }
    class SingerAdapter extends BaseAdapter{
        //어뎁터 초기 세팅
        ArrayList<SingerItem> items = new ArrayList<SingerItem>();

        @Override
        public int getCount() {
            return items.size();

        }

        public void addItem(SingerItem item){
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //메모리가 많이 쓰이기 때문에 다 보여줄 필요는 없음
            SingerItemView view = null;
            if (convertView == null){
                view = new SingerItemView(getApplicationContext());
            } else{
                view = (SingerItemView) convertView;
            }

            SingerItem item = items.get(position);
            view.setName(item.getName());
            view.setMobile(item.getMobile());
            view.setImage(item.getResId());
            return view;
        }
    }

}
