package Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mahmoud.flowersws.Flower;
import com.mahmoud.flowersws.R;
import com.squareup.picasso.Picasso;

/**
 * Created by HP Pro 3500 on 20/11/2016.
 */

public class ItemDetailsFragment extends Fragment {
    final String BASE_URL = "http://services.hanselandpetal.com/photos/";
    ImageView Photo;
    TextView name,cat,price,instructions;

    public static  ItemDetailsFragment getinstance(Flower flower)
    {
        ItemDetailsFragment item = new ItemDetailsFragment();
        Bundle b =new Bundle();
        b.putSerializable("flower",flower);
        item.setArguments(b);
        return  item;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.item_details_fragment,container,false);
        Photo = (ImageView) v.findViewById(R.id.iv_Details_Photo);
        name = (TextView) v.findViewById(R.id.tv_Details_name);
        cat = (TextView) v.findViewById(R.id.tv_Details_Category);
        price = (TextView) v.findViewById(R.id.tv_Details_Price);
        instructions = (TextView) v.findViewById(R.id.tv_Details_instructions);
        Flower flower = (Flower)getArguments().getSerializable("flower");
        name.setText(flower.getName());
        cat.setText(flower.getCategory());
        price.setText(flower.getPrice()+"");
        instructions.setText(flower.getInstructions());
        Picasso.with(getActivity()).load(BASE_URL+flower.getPhoto()).placeholder(R.drawable.loadings).error(R.mipmap.ic_launcher).into(Photo);
        return v;
    }
}
