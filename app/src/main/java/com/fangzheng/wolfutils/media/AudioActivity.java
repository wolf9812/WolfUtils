package com.fangzheng.wolfutils.media;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fangzheng.wolfutils.R;
import com.fangzheng.wolfutils.utils.FileUtils;


public class AudioActivity extends AppCompatActivity {

    private MediaRecorder mAudioRecorder;
    private Button buttonRecord;
    private Button buttonStop;
    private Button buttonPlay;
    private Button buttonSave;

    boolean isPlaying = false;

    private String outputFile = Environment.getExternalStorageDirectory().
            getAbsolutePath() + "/recording.3gp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        buttonRecord = (Button) findViewById(R.id.btn_record);
        buttonStop = (Button) findViewById(R.id.btn_stop);
        buttonPlay = (Button) findViewById(R.id.btn_play);
        buttonSave = (Button) findViewById(R.id.btn_save);
        buttonStop.setEnabled(false);
        buttonPlay.setEnabled(false);

        //检查权限
        //PermissionManager.hasPermission(AudioActivity.this, Manifest.permission.RECORD_AUDIO);

        buttonRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    init();
                    mAudioRecorder.prepare();
                    mAudioRecorder.start();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    buttonRecord.setEnabled(false);
                    buttonStop.setEnabled(true);
                }
                Toast.makeText(getApplicationContext(), "开始录音", Toast.LENGTH_LONG).show();
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mAudioRecorder.stop();
                    //mAudioRecorder.release();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    buttonRecord.setEnabled(true);
                    buttonStop.setEnabled(false);
                    buttonPlay.setEnabled(true);

                }

                Toast.makeText(getApplicationContext(), "录音结束", Toast.LENGTH_LONG).show();
            }
        });


        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying || outputFile == null) {
                    return;
                }
                isPlaying = true;
                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(outputFile);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    buttonRecord.setEnabled(true);
                    isPlaying = false;
                }
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (outputFile == null || isPlaying) {
                    Toast.makeText(AudioActivity.this, "正在录制，请稍后保存", Toast.LENGTH_SHORT).show();
                    return;
                }

                String newFileName = Environment.getExternalStorageDirectory().getAbsolutePath() +
                        "/record/" + System.currentTimeMillis() + "_recording.3gp";
                FileUtils.copy(outputFile, newFileName);
                Toast.makeText(AudioActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 初始化 AudioRecorder
     */
    public void init() {

        mAudioRecorder = new MediaRecorder();
        mAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mAudioRecorder.setOutputFile(outputFile);
    }

    /**
     * 权限申请窗口的回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 123:
                //Toast.makeText(AudioActivity.this,requestCode+"",Toast.LENGTH_SHORT).show();
                if (permissions[0].equals(Manifest.permission.RECORD_AUDIO) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //用户同意
                    buttonRecord.setEnabled(true);
                } else {
                    Log.i("msg", "用户拒绝");
                    buttonRecord.setEnabled(false);
                    Toast.makeText(AudioActivity.this, "请开启录音权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    @Override
    protected void onStop() {
        if (mAudioRecorder != null) {
            mAudioRecorder.release();
        }
        super.onStop();
    }
}
