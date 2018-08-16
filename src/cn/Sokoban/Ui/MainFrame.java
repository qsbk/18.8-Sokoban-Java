package cn.Sokoban.Ui;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/* 
 * 2018/8/16   13：12
 * 修改了数组的的X，Y相反，目的是为了制作地图时更直观
 * 人物向左移动为 Y --；....
 */

public class MainFrame extends Frame implements KeyListener {
	private JLabel lab_Human;//人物
	private int Human_X = 5;//人物位置
	private int Human_Y = 7;
	private JLabel[][] lab_Boxs = new JLabel[11][15];//箱子数组
	private int Score =0;
	
	private int [][] datas = {//地图数据 1-障碍 2-右上角 3-左上角 4-右下角 5-左下角 9-箱子 -1-目标点
			{-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2},
			{-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2},
			{-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2},
			{-2,-2,-2, 3, 1, 1, 1, 2,-2,-2,-2,-2,-2,-2,-2},
			{-2,-2,-2, 1, 0, 0, 0, 5, 2,-2,-2,-2,-2,-2,-2},
			{-2,-2,-2, 1, 0, 0, 9, 0, 1,-2,-2,-2,-2,-2,-2},
			{-2,-2,-2, 5, 2, 9, 0,-1, 1,-2,-2,-2,-2,-2,-2},
			{-2,-2,-2,-2, 1, 0, 0,-1, 1,-2,-2,-2,-2,-2,-2},
			{-2,-2,-2,-2, 5, 1, 1, 1, 4,-2,-2,-2,-2,-2,-2},
			{-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2},
			{-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2}
			
	}; 

	public MainFrame () {
		//人物初始化
		this.HumanInit();
		//箱子初始化
		this.BoxInit(datas);
		//地板 and 目标点初始化 
		this.SetFloor();
		//创建一个JLabe作为背景，并添加到主窗体中
		BackGroundInit();
		//设置主窗体
		this.setMainFrameUI();
		//监听事件
		this.addKeyListener(this);

	}
	//地图数据设置方法

	//设置地板 and 目标点 and 墙
	private void SetFloor() {
		for(int x = 0; x < datas.length; x++) {
			for(int y = 0; y < datas[x].length; y++) {
				if (datas[x][y] == 1 ) {//墙-正方形
					
					JLabel wall = new JLabel(new ImageIcon("./Image/wall-0.png"));
					wall.setBounds(y*50+25, x*50+35, 51, 51);
					this.add(wall);
				}else if(datas[x][y] == 2) {//墙-左下角圆角
				
					JLabel wall = new JLabel(new ImageIcon("./Image/wall-1.png"));
					wall.setBounds(y*50+25, x*50+35, 51, 51);
					this.add(wall);
				}else if(datas[x][y] == 3) {//墙-左上角圆角
					JLabel wall = new JLabel(new ImageIcon("./Image/wall-2.png"));
					wall.setBounds(y*50+25, x*50+35, 51, 51);
					this.add(wall);
				}else if(datas[x][y] == 4) {//墙-右下角圆角
					JLabel wall = new JLabel(new ImageIcon("./Image/wall-3.png"));
					wall.setBounds(y*50+25, x*50+35, 51, 51);
					this.add(wall);
				}else if(datas[x][y] == 5) {//墙-右上角圆角
					JLabel wall = new JLabel(new ImageIcon("./Image/wall-4.png"));
					wall.setBounds(y*50+25, x*50+35, 51, 51);
					this.add(wall);
				}else if(datas[x][y] == -1) {//目标点的初始化
									
					JLabel Aim = new JLabel(new ImageIcon("./Image/aim.png"));
					Aim.setBounds(y*50+25, x*50+35, 51, 51);
					this.add(Aim);
					Score--;

				}
					//地板的初始
				if (datas[x][y] == 0 || datas[x][y] == 9 || datas[x][y] == -1) {
					if ((x+y)%2 == 0) {
						JLabel lab_floor = new JLabel(new ImageIcon("./Image/floor1.png"));
						lab_floor.setBounds(y*50+25, x*50+35, 51, 51);
						this.add(lab_floor);
					}else {
						JLabel lab_floor = new JLabel(new ImageIcon("./Image/floor2.png"));
						lab_floor.setBounds(y*50+25, x*50+35, 51, 51);
						this.add(lab_floor);
					}
				}
			}
		}
	}
	private void HumanInit() {
		//使用Jlabel组建来模拟人物
		lab_Human = new JLabel(new ImageIcon("./Image/human-front.png"));
		lab_Human.setBounds(50*Human_Y +25, 50*Human_X +35, 50, 50); 
		this.add(lab_Human);
	}
	//箱子初始化
	private void BoxInit(int[][] box) {
		for(int x = 0; x < box.length; x++) {
			for(int y = 0; y < box[x].length; y++)
				if( datas[x][y] == 9) {
					JLabel lab_Box = new JLabel(new ImageIcon("./Image/box-no.png"));
					lab_Box.setBounds(y*50+25, x*50+35, 50, 50);
					lab_Boxs[x][y] = lab_Box;
					datas[x][y] = 0;
					this.add(lab_Boxs[x][y]);
				}
			}
		}
	//背景初始化
	private void BackGroundInit() {
		//创建一个图片对象
		Icon i = new ImageIcon("./Image/bg.png");
		JLabel lab_bg = new JLabel(i);
		//设置要添加的组建的位置和大小
		lab_bg.setBounds(0, 0, 800, 600);
		//将这个JLabel添加到窗体中
		this.add(lab_bg);
	}

	//主窗体的设置方法
	private void setMainFrameUI() {
		//设置整个窗口的布局方式为自由布局
		this.setLayout(null);
		this.setTitle("推箱子 v1.0");
		this.setLocation(350, 100);
		this.setSize(800, 600);
		this.setVisible(true);
	}
	@Override
	public void keyPressed(KeyEvent a) {
		//使用lab_Human.getLocation();可以得到人物的位置
		int x = (int) lab_Human.getLocation().getX();
		int y = (int) lab_Human.getLocation().getY();
		
		//key来接收键码
		int key = a.getKeyCode();
		if(key == 32)
			this.dispose();//可用于关闭窗口
		if(Score == 0) 
			JOptionPane.showMessageDialog(null, "游戏胜利", "提示",JOptionPane.WARNING_MESSAGE); 
		
		if(key == 37 && datas[Human_X][Human_Y-1] <= 0) {//左

			//有箱子
			if(lab_Boxs[Human_X][Human_Y-1] != null && lab_Boxs[Human_X][Human_Y-2] == null && datas[Human_X][Human_Y-2] <= 0) {
				lab_Boxs[Human_X][Human_Y-1].setLocation((Human_Y -2)*50+25,(Human_X)*50+35);
				lab_Boxs[Human_X][Human_Y-2] = lab_Boxs[Human_X][Human_Y-1];
				lab_Boxs[Human_X][Human_Y-1] = null;
				Human_Y--;
				lab_Human.setIcon(new ImageIcon("./Image/human-left.png"));			
				lab_Human.setLocation(x -50, y);
				//此处是判断箱子变色问题
				if(datas[Human_X][Human_Y] == 0 && datas[Human_X][Human_Y-1] == -1) {
					lab_Boxs[Human_X][Human_Y-1].setIcon(new ImageIcon("./Image/box-yes.png"));
					Score++;
				}else if(datas[Human_X][Human_Y] == -1 && datas[Human_X][Human_Y-1] == 0){
					lab_Boxs[Human_X][Human_Y-1].setIcon(new ImageIcon("./Image/box-no.png"));
					Score--;
				}
			}
			//没箱子
			if(lab_Boxs[Human_X][Human_Y-1] == null) {
				Human_Y--;
				lab_Human.setIcon(new ImageIcon("./Image/human-left.png"));			
				lab_Human.setLocation(x-50 , y);
			}
		}else if (key == 38 && datas[Human_X-1][Human_Y] <= 0) {//上
			//有箱子
			if(lab_Boxs[Human_X-1][Human_Y] != null && lab_Boxs[Human_X-2][Human_Y] == null && datas[Human_X-2][Human_Y] <= 0) {
				lab_Boxs[Human_X-1][Human_Y].setLocation((Human_Y )*50+25,(Human_X-2)*50+35);
				lab_Boxs[Human_X-2][Human_Y] = lab_Boxs[Human_X-1][Human_Y];
				lab_Boxs[Human_X-1][Human_Y] = null;
				Human_X--;
				lab_Human.setIcon(new ImageIcon("./Image/human-above.png"));			
				lab_Human.setLocation(x, y-50);
				//此处是判断箱子变色问题
				if(datas[Human_X][Human_Y] == 0 && datas[Human_X-1][Human_Y] == -1) {
					lab_Boxs[Human_X-1][Human_Y].setIcon(new ImageIcon("./Image/box-yes.png"));
					Score++;
				}else if(datas[Human_X][Human_Y] == -1 && datas[Human_X-1][Human_Y] == 0){
					lab_Boxs[Human_X-1][Human_Y].setIcon(new ImageIcon("./Image/box-no.png"));
					Score--;
				}
			}
			//没箱子
			if(lab_Boxs[Human_X-1][Human_Y] == null) {
				Human_X--;
				lab_Human.setIcon(new ImageIcon("./Image/human-above.png"));			
				lab_Human.setLocation(x, y-50);
			}
		}else if (key == 39 && datas[Human_X][Human_Y+1] <= 0) {//右
			//有箱子
			if(lab_Boxs[Human_X][Human_Y+1] != null && lab_Boxs[Human_X][Human_Y+2] == null && datas[Human_X][Human_Y+2] <= 0) {
				lab_Boxs[Human_X][Human_Y+1].setLocation((Human_Y +2)*50+25,(Human_X)*50+35);
				lab_Boxs[Human_X][Human_Y+2] = lab_Boxs[Human_X][Human_Y+1];
				lab_Boxs[Human_X][Human_Y+1] = null;
				Human_Y++;
				lab_Human.setIcon(new ImageIcon("./Image/human-right.png"));			
				lab_Human.setLocation(x +50, y);
				//此处是判断箱子变色问题
				if(datas[Human_X][Human_Y] == 0 && datas[Human_X][Human_Y+1] == -1) {
					lab_Boxs[Human_X][Human_Y+1].setIcon(new ImageIcon("./Image/box-yes.png"));
					Score++;
				}else if(datas[Human_X][Human_Y] == -1 && datas[Human_X][Human_Y+1] == 0){
					lab_Boxs[Human_X][Human_Y+1].setIcon(new ImageIcon("./Image/box-no.png"));
					Score--;
				}
			}
			//没箱子
			if(lab_Boxs[Human_X][Human_Y+1] == null) {
				Human_Y++;
				lab_Human.setIcon(new ImageIcon("./Image/human-right.png"));			
				lab_Human.setLocation(x +50, y);
			}
		}else if (key == 40 && datas[Human_X+1][Human_Y] <= 0) {//下
			//有箱子
			if(lab_Boxs[Human_X+1][Human_Y] != null && lab_Boxs[Human_X+2][Human_Y] == null && datas[Human_X+2][Human_Y] <= 0) {
				lab_Boxs[Human_X+1][Human_Y].setLocation((Human_Y )*50+25,(Human_X+2)*50+35);
				lab_Boxs[Human_X+2][Human_Y] = lab_Boxs[Human_X+1][Human_Y];
				lab_Boxs[Human_X+1][Human_Y] = null;
				Human_X++;
				lab_Human.setIcon(new ImageIcon("./Image/human-front.png"));			
				lab_Human.setLocation(x, y+50);
				//此处是判断箱子变色问题
				if(datas[Human_X][Human_Y] == 0 && datas[Human_X+1][Human_Y] == -1) {
					lab_Boxs[Human_X+1][Human_Y].setIcon(new ImageIcon("./Image/box-yes.png"));
					Score++;
				}else if(datas[Human_X][Human_Y] == -1 && datas[Human_X+1][Human_Y] == 0){
					lab_Boxs[Human_X+1][Human_Y].setIcon(new ImageIcon("./Image/box-no.png"));
					Score--;
				}
			}
			//没箱子
			if(lab_Boxs[Human_X+1][Human_Y] == null) {
				Human_X++;
				lab_Human.setIcon(new ImageIcon("./Image/human-front.png"));			
				lab_Human.setLocation(x, y+50);
			}
		}
		

		
	}

	@Override
	public void keyReleased(KeyEvent a) {
		
	}
	@Override
	public void keyTyped(KeyEvent a) {
		
	}
}
