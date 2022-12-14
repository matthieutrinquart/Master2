package Test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.example.projetmobile.Model.Departement;

import java.util.List;

public class ListDepartementRechercheAdaptateur  extends BaseAdapter {

    Integer selected_position = -1;
    private Context context;
    private List<Departement> list;
    private LayoutInflater inflater;

    CheckBox chkbox;

    public ListDepartementRechercheAdaptateur(Context context, List<Departement> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Departement getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.list_departement,null);

        String e = getItem(i).getNom();
        TextView departement = view.findViewById(R.id.Departement);
        chkbox=(CheckBox) view.findViewById(R.id.checkboxdepartement);
        departement.setText(e);
        chkbox.setChecked(getItem(i).isIschecked());
        chkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    getItem(i).setIschecked(true);
                    if(i==0){
                        for(int j=0; j < getCount(); j++){
                            System.out.println(getCount());
                            getItem(j).setIschecked(true);
                        }
                        notifyDataSetChanged();
                    }
                }else{
                    getItem(i).setIschecked(false);
                    if(i==0){
                        for(int j=0; j < getCount(); j++){
                            System.out.println(getCount());
                            getItem(j).setIschecked(false);
                        }
                        notifyDataSetChanged();
                    }
                }
            }
        });
        return view;
    }

}
