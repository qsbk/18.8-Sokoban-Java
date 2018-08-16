package cn.Sokoban.Ui;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/* 
 * 2018/8/16   13��12
 * �޸�������ĵ�X��Y�෴��Ŀ����Ϊ��������ͼʱ��ֱ��
 * ���������ƶ�Ϊ Y --��....
 */

public class MainFrame extends Frame implements KeyListener {
	private JLabel lab_Human;//����
	private int Human_X = 5;//����λ��
	private int Human_Y = 7;
	private JLabel[][] lab_Boxs = new JLabel[11][15];//��������
	private int Score =0;
	
	private int [][] datas = {//��ͼ���� 1-�ϰ� 2-���Ͻ� 3-���Ͻ� 4-���½� 5-���½� 9-���� -1-Ŀ���
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
		//�����ʼ��
		this.HumanInit();
		//���ӳ�ʼ��
		this.BoxInit(datas);
		//�ذ� and Ŀ����ʼ�� 
		this.SetFloor();
		//����һ��JLabe��Ϊ����������ӵ���������
		BackGroundInit();
		//����������
		this.setMainFrameUI();
		//�����¼�
		this.addKeyListener(this);

	}
	//��ͼ�������÷���

	//���õذ� and Ŀ��� and ǽ
	private void SetFloor() {
		for(int x = 0; x < datas.length; x++) {
			for(int y = 0; y < datas[x].length; y++) {
				if (datas[x][y] == 1 ) {//ǽ-������
					
					JLabel wall = new JLabel(new ImageIcon("./Image/wall-0.png"));
					wall.setBounds(y*50+25, x*50+35, 51, 51);
					this.add(wall);
				}else if(datas[x][y] == 2) {//ǽ-���½�Բ��
				
					JLabel wall = new JLabel(new ImageIcon("./Image/wall-1.png"));
					wall.setBounds(y*50+25, x*50+35, 51, 51);
					this.add(wall);
				}else if(datas[x][y] == 3) {//ǽ-���Ͻ�Բ��
					JLabel wall = new JLabel(new ImageIcon("./Image/wall-2.png"));
					wall.setBounds(y*50+25, x*50+35, 51, 51);
					this.add(wall);
				}else if(datas[x][y] == 4) {//ǽ-���½�Բ��
					JLabel wall = new JLabel(new ImageIcon("./Image/wall-3.png"));
					wall.setBounds(y*50+25, x*50+35, 51, 51);
					this.add(wall);
				}else if(datas[x][y] == 5) {//ǽ-���Ͻ�Բ��
					JLabel wall = new JLabel(new ImageIcon("./Image/wall-4.png"));
					wall.setBounds(y*50+25, x*50+35, 51, 51);
					this.add(wall);
				}else if(datas[x][y] == -1) {//Ŀ���ĳ�ʼ��
									
					JLabel Aim = new JLabel(new ImageIcon("./Image/aim.png"));
					Aim.setBounds(y*50+25, x*50+35, 51, 51);
					this.add(Aim);
					Score--;

				}
					//�ذ�ĳ�ʼ
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
		//ʹ��Jlabel�齨��ģ������
		lab_Human = new JLabel(new ImageIcon("./Image/human-front.png"));
		lab_Human.setBounds(50*Human_Y +25, 50*Human_X +35, 50, 50); 
		this.add(lab_Human);
	}
	//���ӳ�ʼ��
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
	//������ʼ��
	private void BackGroundInit() {
		//����һ��ͼƬ����
		Icon i = new ImageIcon("./Image/bg.png");
		JLabel lab_bg = new JLabel(i);
		//����Ҫ��ӵ��齨��λ�úʹ�С
		lab_bg.setBounds(0, 0, 800, 600);
		//�����JLabel��ӵ�������
		this.add(lab_bg);
	}

	//����������÷���
	private void setMainFrameUI() {
		//�����������ڵĲ��ַ�ʽΪ���ɲ���
		this.setLayout(null);
		this.setTitle("������ v1.0");
		this.setLocation(350, 100);
		this.setSize(800, 600);
		this.setVisible(true);
	}
	@Override
	public void keyPressed(KeyEvent a) {
		//ʹ��lab_Human.getLocation();���Եõ������λ��
		int x = (int) lab_Human.getLocation().getX();
		int y = (int) lab_Human.getLocation().getY();
		
		//key�����ռ���
		int key = a.getKeyCode();
		if(key == 32)
			this.dispose();//�����ڹرմ���
		if(Score == 0) 
			JOptionPane.showMessageDialog(null, "��Ϸʤ��", "��ʾ",JOptionPane.WARNING_MESSAGE); 
		
		if(key == 37 && datas[Human_X][Human_Y-1] <= 0) {//��

			//������
			if(lab_Boxs[Human_X][Human_Y-1] != null && lab_Boxs[Human_X][Human_Y-2] == null && datas[Human_X][Human_Y-2] <= 0) {
				lab_Boxs[Human_X][Human_Y-1].setLocation((Human_Y -2)*50+25,(Human_X)*50+35);
				lab_Boxs[Human_X][Human_Y-2] = lab_Boxs[Human_X][Human_Y-1];
				lab_Boxs[Human_X][Human_Y-1] = null;
				Human_Y--;
				lab_Human.setIcon(new ImageIcon("./Image/human-left.png"));			
				lab_Human.setLocation(x -50, y);
				//�˴����ж����ӱ�ɫ����
				if(datas[Human_X][Human_Y] == 0 && datas[Human_X][Human_Y-1] == -1) {
					lab_Boxs[Human_X][Human_Y-1].setIcon(new ImageIcon("./Image/box-yes.png"));
					Score++;
				}else if(datas[Human_X][Human_Y] == -1 && datas[Human_X][Human_Y-1] == 0){
					lab_Boxs[Human_X][Human_Y-1].setIcon(new ImageIcon("./Image/box-no.png"));
					Score--;
				}
			}
			//û����
			if(lab_Boxs[Human_X][Human_Y-1] == null) {
				Human_Y--;
				lab_Human.setIcon(new ImageIcon("./Image/human-left.png"));			
				lab_Human.setLocation(x-50 , y);
			}
		}else if (key == 38 && datas[Human_X-1][Human_Y] <= 0) {//��
			//������
			if(lab_Boxs[Human_X-1][Human_Y] != null && lab_Boxs[Human_X-2][Human_Y] == null && datas[Human_X-2][Human_Y] <= 0) {
				lab_Boxs[Human_X-1][Human_Y].setLocation((Human_Y )*50+25,(Human_X-2)*50+35);
				lab_Boxs[Human_X-2][Human_Y] = lab_Boxs[Human_X-1][Human_Y];
				lab_Boxs[Human_X-1][Human_Y] = null;
				Human_X--;
				lab_Human.setIcon(new ImageIcon("./Image/human-above.png"));			
				lab_Human.setLocation(x, y-50);
				//�˴����ж����ӱ�ɫ����
				if(datas[Human_X][Human_Y] == 0 && datas[Human_X-1][Human_Y] == -1) {
					lab_Boxs[Human_X-1][Human_Y].setIcon(new ImageIcon("./Image/box-yes.png"));
					Score++;
				}else if(datas[Human_X][Human_Y] == -1 && datas[Human_X-1][Human_Y] == 0){
					lab_Boxs[Human_X-1][Human_Y].setIcon(new ImageIcon("./Image/box-no.png"));
					Score--;
				}
			}
			//û����
			if(lab_Boxs[Human_X-1][Human_Y] == null) {
				Human_X--;
				lab_Human.setIcon(new ImageIcon("./Image/human-above.png"));			
				lab_Human.setLocation(x, y-50);
			}
		}else if (key == 39 && datas[Human_X][Human_Y+1] <= 0) {//��
			//������
			if(lab_Boxs[Human_X][Human_Y+1] != null && lab_Boxs[Human_X][Human_Y+2] == null && datas[Human_X][Human_Y+2] <= 0) {
				lab_Boxs[Human_X][Human_Y+1].setLocation((Human_Y +2)*50+25,(Human_X)*50+35);
				lab_Boxs[Human_X][Human_Y+2] = lab_Boxs[Human_X][Human_Y+1];
				lab_Boxs[Human_X][Human_Y+1] = null;
				Human_Y++;
				lab_Human.setIcon(new ImageIcon("./Image/human-right.png"));			
				lab_Human.setLocation(x +50, y);
				//�˴����ж����ӱ�ɫ����
				if(datas[Human_X][Human_Y] == 0 && datas[Human_X][Human_Y+1] == -1) {
					lab_Boxs[Human_X][Human_Y+1].setIcon(new ImageIcon("./Image/box-yes.png"));
					Score++;
				}else if(datas[Human_X][Human_Y] == -1 && datas[Human_X][Human_Y+1] == 0){
					lab_Boxs[Human_X][Human_Y+1].setIcon(new ImageIcon("./Image/box-no.png"));
					Score--;
				}
			}
			//û����
			if(lab_Boxs[Human_X][Human_Y+1] == null) {
				Human_Y++;
				lab_Human.setIcon(new ImageIcon("./Image/human-right.png"));			
				lab_Human.setLocation(x +50, y);
			}
		}else if (key == 40 && datas[Human_X+1][Human_Y] <= 0) {//��
			//������
			if(lab_Boxs[Human_X+1][Human_Y] != null && lab_Boxs[Human_X+2][Human_Y] == null && datas[Human_X+2][Human_Y] <= 0) {
				lab_Boxs[Human_X+1][Human_Y].setLocation((Human_Y )*50+25,(Human_X+2)*50+35);
				lab_Boxs[Human_X+2][Human_Y] = lab_Boxs[Human_X+1][Human_Y];
				lab_Boxs[Human_X+1][Human_Y] = null;
				Human_X++;
				lab_Human.setIcon(new ImageIcon("./Image/human-front.png"));			
				lab_Human.setLocation(x, y+50);
				//�˴����ж����ӱ�ɫ����
				if(datas[Human_X][Human_Y] == 0 && datas[Human_X+1][Human_Y] == -1) {
					lab_Boxs[Human_X+1][Human_Y].setIcon(new ImageIcon("./Image/box-yes.png"));
					Score++;
				}else if(datas[Human_X][Human_Y] == -1 && datas[Human_X+1][Human_Y] == 0){
					lab_Boxs[Human_X+1][Human_Y].setIcon(new ImageIcon("./Image/box-no.png"));
					Score--;
				}
			}
			//û����
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
