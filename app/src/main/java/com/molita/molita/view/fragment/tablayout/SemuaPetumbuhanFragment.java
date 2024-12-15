package com.molita.molita.view.fragment.tablayout;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.molita.molita.R;
import com.molita.molita.model.data.PertumbuhanModel;
import com.molita.molita.model.repository.PertumbuhanRepository;
import com.molita.molita.utils.TumbuhHelper;
import com.molita.molita.view.fragment.GrafikFragment;
import com.molita.molita.viewmodel.Pertumbuhan.PertumbuhanViewModel;

import java.util.ArrayList;
import java.util.List;


public class SemuaPetumbuhanFragment extends Fragment {

    private LineChart lineChart;
    private PertumbuhanViewModel tabViewModel;
    String idAnak;
    TextView tabContent;
    int umurAnak;
    float beratBadan = 0;
    float tinggiBadan = 0;

    public SemuaPetumbuhanFragment(String idAnak, int umurAnak) {
        this.idAnak = idAnak;
        this.umurAnak = umurAnak;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_semua_pertumbuhan, container, false);

        lineChart = view.findViewById(R.id.lineChart);
        tabContent = view.findViewById(R.id.tabContent);

        tabViewModel = new ViewModelProvider(this).get(PertumbuhanViewModel.class);

        tabContent.setText("-");

        // Observe data pertumbuhan
        tabViewModel.getPertumbuhanList().observe(getViewLifecycleOwner(), growthData -> {
            if (growthData != null) {
                loadAreaChartData(growthData);

                PertumbuhanModel pertumbuhanModel = growthData.get(growthData.size() - 1);
                beratBadan = pertumbuhanModel.getBerat_badan();
                tinggiBadan = pertumbuhanModel.getTinggi_badan();

                String keterangan = TumbuhHelper.getGrowthDescription(umurAnak, beratBadan,
                        tinggiBadan);
                tabContent.setText(keterangan);
            }
        });

        tabViewModel.getPertumbuhanByAnak(idAnak);

        configureChart();
        return view;
    }

    private void loadAreaChartData(List<PertumbuhanModel> growthDataList) {
        ArrayList<Entry> weightEntries = new ArrayList<>();
        ArrayList<Entry> heightEntries = new ArrayList<>();
        final List<String> months = new ArrayList<>();

        for (int i = 0; i < growthDataList.size(); i++) {
            PertumbuhanModel data = growthDataList.get(i);
            months.add(data.getBulan_pencatatan());
            weightEntries.add(new Entry(i, data.getBerat_badan()));
            heightEntries.add(new Entry(i, data.getTinggi_badan()));
        }

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if (value >= 0 && value < months.size()) {
                    return months.get((int) value);
                }
                return "";
            }
        });

        LineDataSet weightDataSet = new LineDataSet(weightEntries, "Berat Badan");
        customizeDataSet(weightDataSet, "#95CBDA");

        LineDataSet heightDataSet = new LineDataSet(heightEntries, "Tinggi Badan");
        customizeDataSet(heightDataSet, "#098DB3");

        LineData lineData = new LineData(weightDataSet, heightDataSet);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }

    private void customizeDataSet(LineDataSet dataSet, String color) {
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.setColor(Color.parseColor(color));
        dataSet.setLineWidth(2f);
        dataSet.setDrawFilled(true);
        dataSet.setFillColor(Color.parseColor(color));
        dataSet.setFillAlpha(150);
        dataSet.setValueTextColor(Color.BLACK);
    }

    private void configureChart() {
        // Mendapatkan XAxis dan YAxis dari chart
        XAxis xAxis = lineChart.getXAxis();
        YAxis leftAxis = lineChart.getAxisLeft();
        YAxis rightAxis = lineChart.getAxisRight();

        // Menonaktifkan grid garis vertikal di sumbu X
        xAxis.setDrawGridLines(false);

        // Menonaktifkan grid garis horizontal di sumbu kiri (YAxis)
        leftAxis.setDrawGridLines(false);

        // Menyembunyikan sumbu kanan (kanan dari chart)
        rightAxis.setEnabled(false);

        // Menampilkan garis sumbu X (bawah)
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true); // Menampilkan garis bawah

        // Menampilkan garis vertikal di sisi kiri
        leftAxis.setDrawAxisLine(true); // Menampilkan garis di sumbu Y kiri

        // Menghapus deskripsi di chart
        lineChart.getDescription().setEnabled(false);
        lineChart.setDrawGridBackground(false); // Menghilangkan background grid

        // Mengubah warna teks nilai di sumbu Y
        leftAxis.setTextColor(Color.parseColor("#098DB3")); // Mengubah warna teks sumbu Y
        xAxis.setTextColor(Color.parseColor("#098DB3")); // Mengubah warna teks sumbu X
    }
}