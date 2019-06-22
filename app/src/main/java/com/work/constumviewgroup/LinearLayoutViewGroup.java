package com.work.constumviewgroup;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LinearLayoutViewGroup extends ViewGroup {
    private Context context;
    private static boolean isMatchParent;

    /**设置是否需要宽度填充屏幕*/
    public static void setIsMatchParent(boolean isMatchParents){
        isMatchParent = isMatchParents;
    }

    public LinearLayoutViewGroup(Context context) {
        this(context,null);
        this.context = context;
    }

    public LinearLayoutViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        this.context = context;
    }

    public LinearLayoutViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    /**获取视图中的子视图的个数，根据子视图来设置父视图的大小*/
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            //测量子控件的宽高
            measureChildren(widthMeasureSpec, heightMeasureSpec);
            int width = getResources().getDisplayMetrics().widthPixels;
            //子控件孩子宽度和
            int sumChildWidth = 0;
            //本行子控件的记录
            int childMaxHeight = 0;
            //最宽的子控件宽度和
            int childMaxWidth = 0;
            //记录孩子控件的总高度
            int sumChildHight = 0;

            //获取子控件的个数
            int childCount = getChildCount();
            Log.e("tag", "测量孩子个数:" + childCount);
            //循环遍历子控件，计算其大小总和
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);//获取子控件
                //获取子控件的宽高
                int childWidth = childView.getMeasuredWidth();
                int childHeight = childView.getMeasuredHeight();
                //获取孩子控件的padding值
                int childPaddingLeft = childView.getPaddingLeft();
                int childPaddingTop = childView.getPaddingTop();
                int childPaddingBotton = childView.getPaddingBottom();
                int childPaddingRight = childView.getPaddingRight();
                //叠加子控件宽度，进行排版,如果孩子控件宽度和>父控件宽度则进行换行
                if (width < sumChildWidth + childWidth + childPaddingLeft + childPaddingRight) {
                    //换行
                    //判断剩余空间然后均分给本行子控件

                    childMaxWidth = Math.max(sumChildWidth, childMaxWidth);//保存最大宽度
                    sumChildWidth = 0;//换行后宽度置零
                    sumChildHight += childMaxHeight + childPaddingTop + childPaddingBotton;//保存下一行子控件高度
                } else {
                    childMaxHeight = Math.max(childHeight, childMaxHeight);//保存之前行最高子控件高度

                }
                if (i == childCount - 1) {
                    //表示最后一个子控件
                    sumChildHight += childMaxHeight + childPaddingTop + childPaddingBotton;
                }
                //统计每行的子控件宽度和
                sumChildWidth += childWidth + childPaddingLeft + childPaddingRight;

                Log.e("tag", "childWidth:" + childWidth + ":childHeight:" + childHeight);
                Log.e("tag", i + ":sumChildWidth:" + sumChildWidth + ":childMaxheight:" + childMaxHeight);

            }
            Log.e("tag", "父控件的大小：" + childMaxWidth + ":" + sumChildHight);

            //将记录到的宽度高度总和设置为父布局
            setMeasuredDimension(childMaxWidth, sumChildHight);
    }

    /**设置子控件在父布局中的位置*/
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int childCount = getChildCount();
        Log.e("tag","孩子个数："+childCount);
        int width = getMeasuredWidth();
        //子控件孩子宽度和
        int sumChildWidth = 0;
        //本行子控件的记录
        int childMaxHeight = 0;
        //最宽的子控件宽度和
        int childMaxWidth = 0;
        //记录孩子控件的总高度
        int sumChildHight = 0;
        //记录孩子控件的左边值
        int left = 0;
        //记录孩子控件的上边值
        int top = 0;
        //记录孩子控件的右边值
        int right = 0;
        //记录孩子控件的下边值
        int buttom = 0;
        //记录当前行中列数的标记
        int indexTag = 0;
        //保存之前一个子控件
        View lastView = null;
        //依旧循环遍历子控件，为每一个子控件设置位置
        for (int i = 0 ; i < childCount ; i++ ){
            View childView = getChildAt(i);
            View nextChildView = getChildAt(i+1);
            //获取子控件宽高
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();
            //获取孩子控件的padding值
            int childPaddingLeft = childView.getPaddingLeft();
            int childPaddingTop = childView.getPaddingTop();
            int childPaddingBotton = childView.getPaddingBottom();
            int childPaddingRight = childView.getPaddingRight();
           /**如果控件宽度和>父控件宽度,进行换行*/
           Log.e("tag","父控件宽度:"+width+":"+(right + childPaddingLeft + childPaddingRight +childWidth));
           if (width < right + childPaddingLeft + childPaddingRight +childWidth){
               //判断添加一个子控件超宽了，那么将本行的剩余空间均分给本行的子控件

               lastView.layout(left ,top,width,buttom);

                indexTag=0;
                left = childPaddingLeft;
               top = buttom + childPaddingTop;
               right = left + childWidth ;
               buttom = top + childHeight + childPaddingBotton;
           }else{/**未超宽*/
                if (indexTag ==0){
                    left = childPaddingLeft;
                }else{
                    left = right + childPaddingLeft;//本行非第一个子控件
                }

                right = left + childWidth + childPaddingLeft;

                buttom = top + childHeight + childPaddingTop;

                if (i == childCount-1 && width - right >0){
                    //当最后一个控件添加后还有剩余空间，则将空间分配给最后一个view
                    right = width;
                }


           }
           Log.e("tag",i+":left:"+left+":top:"+top+":right:"+right+":bottom:"+buttom);
            childView.layout(left,top,right,buttom);
            indexTag++;
           Log.e("tag","childWidth:"+childWidth+":childHeight:"+childHeight);
           lastView = childView;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
