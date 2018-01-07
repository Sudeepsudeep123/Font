package dev.edmt.advancedsearchbar;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sudeepbajracharya on 11/30/17.
 */

public class CustomAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<GroupInfo> deptList;

    public CustomAdapter(Context context, ArrayList<GroupInfo> deptList) {
        this.context = context;
        this.deptList = deptList;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<ChildInfo> productList = deptList.get(groupPosition).getProductList();
        return productList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        ChildInfo detailInfo = (ChildInfo) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.list_item, null);
        }

        //TextView sequence = (TextView) view.findViewById(R.id.sequence);
       //sequence.setText(detailInfo.getSequence() + ". ");
        TextView childItem = (TextView) view.findViewById(R.id.childItem);
       // childItem.setBackground();
        childItem.setText("                        "+detailInfo.getName());

//        Typeface typeChild = Typeface.createFromAsset(context.getAssets(), "fonts/ABeeZee Italic.ttf");
  //      childItem.setTypeface(typeChild);
        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        ArrayList<ChildInfo> productList = deptList.get(groupPosition).getProductList();
        return productList.size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return deptList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return deptList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view,
                             ViewGroup parent) {

        GroupInfo headerInfo = (GroupInfo) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.group_items, null);

        }

        TextView heading = (TextView) view.findViewById(R.id.heading);

        // ** trying to extract fonts from url

        //Uri url = Uri.parse("http://fonts.gstatic.com/s/abeezee/v11/kpplLynmYgP0YtlJA3atRw.ttf");
       // Typeface typeface = ResourcesCompat.getFont(this, R.font.app_font);
       // Typeface typeface = Typeface.createFromFile()
        // Typeface typeFace = Typeface.createFromAsset(context, url);

        // ** the ABeZee font was in assets in TestJson

       // Typeface typeFace = Typeface.createFromAsset(context.getAssets(), "fonts/ABeZee.ttf");
       // heading.setTypeface(typeFace);

        heading.setText(headerInfo.getName().trim());

        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}