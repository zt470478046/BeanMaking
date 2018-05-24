package com.myworld.android.beanmaking.circle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2018/5/14.
 */
@SuppressLint("AppCompatCustomView")
public class CircleView extends ImageView {
    private Paint paint;
    private Matrix matrix;
    private BitmapShader shader;
    //声明Bitmap，用来记录用户有没有已经会画过视图
    private Bitmap mBiamtp;
    public CircleView(Context context) {
        this(context,null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //对图片进行缩放
        matrix = new Matrix();
    }
    //绘画

    @Override
    protected void onDraw(Canvas canvas) {
        //获取Imageview上的图片
        Drawable drawable = getDrawable();
        //将获取的过程封装成一个方法
        Bitmap bitmap = getDrawable(drawable);
        if (bitmap!=null){
            //去计算控件的宽度和高度
            int width = getWidth();
            int height = getHeight();
            float minSize = Math.min(width,height);
            //判断shader是否为空
            if (shader==null || !bitmap.equals(mBiamtp)){
                mBiamtp = bitmap;
                //如果shader为空 则进行创建
                shader = new BitmapShader(mBiamtp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            }
            if (shader!=null){
                //计算缩放比
                matrix.setScale(minSize/mBiamtp.getWidth(),minSize/mBiamtp.getHeight());
                //把矩阵设置shader
                shader.setLocalMatrix(matrix);
            }
            //将shader设置给画笔
            paint.setShader(shader);
            //画圆
            float  radius = minSize/2;
            canvas.drawCircle(radius,radius,radius,paint);
        }else{
            super.onDraw(canvas);
        }
    }
    //该方法用来将drawable转换称bitmap
    private Bitmap getDrawable(Drawable drawable) {
        if (drawable instanceof BitmapDrawable){
            return ((BitmapDrawable) drawable).getBitmap();
        }else if (drawable instanceof ColorDrawable){
            //获取Draw able所对应的矩形框
            Rect bounds = drawable.getBounds();
            int dWidth = bounds.width();
            int dHeight = bounds.height();

            //通过Bitmap创建位图
            Bitmap bitmap = Bitmap.createBitmap(
                    dWidth,dHeight, Bitmap.Config.ARGB_8888);
            //创建画布，绘制位图
            Canvas canvas = new Canvas(bitmap);
            int color = ((ColorDrawable)drawable).getColor();
            canvas.drawARGB(Color.alpha(color),Color.red(color),
                    Color.green(color),Color.blue(color));
            return bitmap;
        }else{
            return null;
        }
    }
}
