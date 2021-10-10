package com.example.dodamver3;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

class CalendarAdapter extends BaseAdapter {
    private ArrayList<DayInfo> mDayList;
    private Context mContext;
    private int mResource;
    private LayoutInflater mLiInflater;

    /**
     * Adpater 생성자
     *
     * @param context      컨텍스트
     * @param textResource 레이아웃 리소스
     * @param dayList      날짜정보가 들어있는 리스트
     */
    public CalendarAdapter(Context context, int textResource, ArrayList<DayInfo> dayList) {
        this.mContext = context;
        this.mDayList = dayList;
        this.mResource = textResource;
        this.mLiInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mDayList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mDayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DayInfo day = mDayList.get(position);

        DayViewHolder dayViewHolder;

        if (convertView == null) {
            convertView = mLiInflater.inflate(mResource, null);

            /*if (position % 7 == 6) {
                convertView.setLayoutParams(new GridView.LayoutParams(getCellWidthDP() + getRestCellWidthDP(), getCellHeightDP()));
            } else {
                convertView.setLayoutParams(new GridView.LayoutParams(getCellWidthDP(), getCellHeightDP()));
            }*/


            dayViewHolder = new DayViewHolder();

            dayViewHolder.llBackground = (LinearLayout) convertView.findViewById(R.id.day_cell_ll_background);
            dayViewHolder.tvDay = (TextView) convertView.findViewById(R.id.day_cell_tv_day);

            convertView.setTag(dayViewHolder);
        } else {
            dayViewHolder = (DayViewHolder) convertView.getTag();
        }

        if (day != null) {
            dayViewHolder.tvDay.setText(day.getDay());

            if (day.isInMonth()) {
                if (position % 7 == 0) {
                    dayViewHolder.tvDay.setTextColor(Color.BLACK);
                    dayViewHolder.tvDay.setTextColor(Color.parseColor( "#f58989" ));
                } else if (position % 7 == 6) {
                    dayViewHolder.tvDay.setTextColor(Color.BLACK);
                    dayViewHolder.tvDay.setTextColor(Color.parseColor( "#79a9a9" ));
                } else {
                    dayViewHolder.tvDay.setTextColor(Color.BLACK);
                }
            } else {
                dayViewHolder.tvDay.setTextColor(Color.GRAY);
            }

        }

        return convertView;
    }

    public class DayViewHolder {
        public LinearLayout llBackground;
        public TextView tvDay;

    }
    /*
    private int getCellWidthDP() {
//      int width = mContext.getResources().getDisplayMetrics().widthPixels;
        int cellWidth = 600 / 7;
        return cellWidth;
    }
    private int getRestCellWidthDP() {
//      int width = mContext.getResources().getDisplayMetrics().widthPixels;
        int cellWidth = 600 % 7;
        return cellWidth;
    }
    private int getCellHeightDP() {
//      int height = mContext.getResources().getDisplayMetrics().widthPixels;
        int cellHeight = 600 / 6;
        return cellHeight;
    }
    */
}