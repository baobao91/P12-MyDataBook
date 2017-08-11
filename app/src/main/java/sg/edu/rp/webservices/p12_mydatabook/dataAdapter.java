package sg.edu.rp.webservices.p12_mydatabook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by 126308 on 11/8/2017.
 */

public class dataAdapter extends ArrayAdapter<dataBook> {

    private ArrayList<dataBook> books;
    private TextView tvTitle;
    private ImageView ivIcon;
    private Context context;

    public dataAdapter(Context context, int resource, ArrayList<dataBook> objects) {
        super(context, resource, objects);

        books = objects;

        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // The usual way to get the LayoutInflater object to
        //  "inflate" the XML file into a View object
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // "Inflate" the row.xml as the layout for the View object
        View rowView = inflater.inflate(R.layout.row, parent, false);

        // Get the TextView object
        tvTitle = (TextView) rowView.findViewById(R.id.textViewTitle);
        ivIcon = (ImageView) rowView.findViewById(R.id.imageViewIcon);

        dataBook currentBooks = books.get(position);

        if (currentBooks.getTitle().equalsIgnoreCase("Bio")) {
            ivIcon.setImageResource(R.drawable.info);
        } else if (currentBooks.getTitle().equalsIgnoreCase("Vaccination")) {
            ivIcon.setImageResource(R.drawable.jab);
        } else if (currentBooks.getTitle().equalsIgnoreCase("Anniversary")) {
            ivIcon.setImageResource(R.drawable.calendar);
        } else {
            ivIcon.setImageResource(R.drawable.stars);
        }

        tvTitle.setText(currentBooks.getTitle());
        return rowView;
    }
}
