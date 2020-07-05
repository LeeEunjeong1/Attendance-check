package com.example.ycheck.ui.check;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.ycheck.R;
import com.example.ycheck.p_checknow;
import com.example.ycheck.p_noclass;

public class p_CheckListFragment extends Fragment {
    private p_CheckListViewModel pCheckListViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pCheckListViewModel =
                ViewModelProviders.of(this).get(p_CheckListViewModel.class);
        View root = inflater.inflate(R.layout.p_fragment_check, container, false);

        Button btn1 = (Button) root.findViewById(R.id.btn1);
        final Button btn2 = (Button) root.findViewById(R.id.btn2);
        Button btn3 = (Button) root.findViewById(R.id.btn3);

        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext()); // 빌더 얻기

                // 다이얼로그 메세지 생성
                alertDialogBuilder
                        .setMessage("보강날짜를 설정하시겠습니까?")
                        .setPositiveButton("예", // Positive 버튼 기능 작성
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent registerIntent = new Intent(getActivity(), p_noclass.class);
                                        getActivity().startActivity(registerIntent);
                                    }
                                })
                        .setNegativeButton("아니오", //Negative 버튼 기능 작성
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel(); // 다이얼로그 취소
                                    }
                                });

                // 다이럴로그 객체 얻어오기
                AlertDialog alertDialog = alertDialogBuilder.create();

                // 다이얼로그 보여주기
                alertDialog.show();

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext()); // 빌더 얻기

                // 다이얼로그 메세지 생성
                alertDialogBuilder
                        .setMessage("출석 현황을 확인하시겠습니까?")
                        .setPositiveButton("예", // Positive 버튼 기능 작성
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent registerIntent = new Intent(getActivity(), p_checknow.class);
                                        getActivity().startActivity(registerIntent);
                                    }
                                })
                        .setNegativeButton("아니오", //Negative 버튼 기능 작성
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel(); // 다이얼로그 취소
                                    }
                                });

                // 다이럴로그 객체 얻어오기
                AlertDialog alertDialog = alertDialogBuilder.create();

                // 다이얼로그 보여주기
                alertDialog.show();

            }
        });
        return root;
    }
}
