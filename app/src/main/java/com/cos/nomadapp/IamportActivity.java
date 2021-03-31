package com.cos.nomadapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.cos.nomadapp.model.courses.Course;
import com.iamport.sdk.data.sdk.IamPortRequest;
import com.iamport.sdk.data.sdk.PG;
import com.iamport.sdk.data.sdk.PayMethod;
import com.iamport.sdk.domain.core.Iamport;

import java.util.Date;

import kotlin.Unit;
import retrofit2.Retrofit;

public class IamportActivity extends AppCompatActivity {

    private static final String TAG = "IamportActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iamport);

        Intent intent = getIntent();
        Course course = (Course) intent.getSerializableExtra("course");

        Iamport.INSTANCE.init(this);

        IamPortRequest request = IamPortRequest.builder()
                .pg(PG.nice.makePgRawName(""))
                .pay_method(PayMethod.card)
                .name(course.getTitle())
                .merchant_uid("mid_" + (new Date()).getTime())
                .amount(course.getPrice())
                .buyer_name("김아임포트").build();

        Iamport.INSTANCE.payment("imp19424728", request,
                iamPortApprove -> {
                    // (Optional) CHAI 최종 결제전 콜백 함수.
                    return Unit.INSTANCE;
                }, iamPortResponse -> {
                    // 최종 결제결과 콜백 함수.
                    String responseText = iamPortResponse.toString();
                    Log.d("IAMPORT_SAMPLE", responseText);
                    Toast.makeText(this, responseText, Toast.LENGTH_LONG).show();
                    return Unit.INSTANCE;
                });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        Iamport.INSTANCE.close();
    }
}