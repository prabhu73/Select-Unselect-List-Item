package com.javaapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class LabTestAdapater extends RecyclerView.Adapter<LabTestAdapater.LabTestViewHolder> {

    private List<TestListData> testListDataList;
    private LabTestSelectionCallback selectionCallback;
    private Context context;

    public LabTestAdapater(Context context, List<TestListData> testListDataList, LabTestSelectionCallback selectionCallback) {
        this.testListDataList = testListDataList;
        this.selectionCallback = selectionCallback;
        this.context = context;
    }

    @NonNull
    @Override
    public LabTestViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LabTestViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_testbooking, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final LabTestViewHolder labTestViewHolder, int i) {
        final TestListData listData = testListDataList.get(i);

        labTestViewHolder.testTitle.setText(listData.getName());
        labTestViewHolder.testPrice.setText(String.valueOf(listData.getAmount()));
        final int position = i;
        labTestViewHolder.containerLayout.setBackground(listData.getItemSelected() ? context.getDrawable(R.color.design_default_color_primary)
                : context.getDrawable(R.color.design_fab_stroke_top_outer_color));
        labTestViewHolder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!listData.getItemSelected()) {
                    listData.setItemSelected(true);
                } else {
                    listData.setItemSelected(false);
                }
                notifyItemChanged(position);
                selectionCallback.onLabTestItemSelected(listData);
            }
        });
    }

    @Override
    public int getItemCount() {
        return testListDataList.size();
    }

    class LabTestViewHolder extends RecyclerView.ViewHolder {

        private TextView testTitle;
        private TextView testPrice;
        private Button addBtn;
        private RelativeLayout containerLayout;

        public LabTestViewHolder(@NonNull View itemView) {
            super(itemView);
            testTitle = itemView.findViewById(R.id.texttitle);
            testPrice = itemView.findViewById(R.id.testprice);
            addBtn = itemView.findViewById(R.id.img_add);
            containerLayout = itemView.findViewById(R.id.rl_add);
        }
    }
}
