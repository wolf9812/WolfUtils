package com.fangzheng.wolfutils.permission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.fangzheng.wolfutils.R;
import com.fangzheng.wolfutils.media.AudioActivity;

public class PermissionApplyActivity extends AppCompatActivity {

    private static final int CAMERA_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_apply);
    }

    public void click(View view) {

        String permission = Manifest.permission.CAMERA;

        switch (view.getId()) {
            case R.id.btn_check_camera_permission:


                boolean flag = checkPermission(permission);
                String res = flag?"权限打开状态":"权限关闭状态";
                Toast.makeText(PermissionApplyActivity.this,res,Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_on_camera_permission:
                //打开权限
                turnOnPermission(permission);
                break;
            case R.id.btn_off_camera_permission:
                //关闭权限
                turnOffPermission(permission);
                break;
        }
    }

    /**
     * 打开权限
     * @param permission
     */
    public void turnOnPermission(String permission){
        if (!checkPermission(permission)) {
            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{
                    permission}, CAMERA_PERMISSION_CODE);
        } else {
            Toast.makeText(PermissionApplyActivity.this, "权限已经打开", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 关闭权限
     * @param permission
     */
    public void turnOffPermission(String permission){

    }

    /**
     * 检测权限是否打开
     *
     * @param permission
     */
    public boolean checkPermission(String permission) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }

        if (ContextCompat.checkSelfPermission(this, permission)
                != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    /**
     * 此方法在你调用权限检测后回调的函数
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case CAMERA_PERMISSION_CODE:
                if (permissions[0].equals(Manifest.permission.CAMERA) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //用户同意
                    Toast.makeText(PermissionApplyActivity.this, "权限已经打开", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PermissionApplyActivity.this, "请打开权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }
}
