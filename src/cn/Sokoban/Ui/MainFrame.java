package cn.Sokoban.Ui;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class MainFrame extends Frame implements KeyListener {
	private JLabel lab_Human;//����
	private int Human_X = 8;//����λ��
	private int Human_Y = 4;
	private JLabel[][] lab_Boxs = new JLabel[15][11];//��������
	private int Score =0;
	
	private int [][] datas = {//��ͼ���� 1-�ϰ�1 2-���½� 3-���Ͻ� 4-���½� 5-���Ͻ�  9-���� -1-Ŀ���
			{-2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2},
			{-2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2},
			{-2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2},
			{-2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2},
			{-2, -2, -2, -2,  3,  1,  1,  1,  5, -2, -2},
			{-2, -2, -2,  3,  4,  0, -1,  0,  1, -2, -2},
			{-2, -2, -2,  1,  0,  9,  0, -1,  1, -2, -2},
			{-2, -2, -2,  1,  0,  0,  9,  0,  1, -2, -2},
			{-2, -2, -2,  1,  0,  0,  0,  3,  4, -2, -2},
			{-2, -2, -2,  2,  1,  1,  1,  4, -2, -2, -2},
			{-2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2},
			{-2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2},
			{-2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2},
			{-2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2},
			{-2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2}
			
	}; 

	public MainFrame () {
		//�����ʼ��
		this.HumanInit();
		//���ӳ�ʼ��
		this.BoxInit(datas);
		//�ذ� and Ŀ���ʼ�� 
		this.SetFloor();
		//����һ��JLabe��Ϊ����������ӵ���������
		BackGroundInit();
		//����������
		this.setMainFrameUI();
		//�����¼�
		this.addKeyListener(this);

	}
	//��ͼ�������÷���

	//���õذ� and Ŀ�� and ǽ
	private void SetFloor() {
		for(int i = 0; i < datas.length; i++) {
			for(int j = 0; j < datas[i].length; j++) {
				if (datas[i][j] == 1 ) {//ǽ
					
					JLabel wall = new JLabel(new ImageIcon("wall-0.png"));
					wall.setBounds(i*50+25, j*50+35, 51, 51);
					this.add(wall);
				}else if(datas[i][j] == 2) {
				
					JLabel wall = new JLabel(new ImageIcon("wall-1.png"));
					wall.setBounds(i*50+25, j*50+35, 51, 51);
					this.add(wall);
				}else if(datas[i][j] == 3) {
					JLabel wall = new JLabel(new ImageIcon("wall-2.png"));
					wall.setBounds(i*50+25, j*50+35, 51, 51);
					this.add(wall);
				}else if(datas[i][j] == 4) {
					JLabel wall = new JLabel(new ImageIcon("wall-3.png"));
					wall.setBounds(i*50+25, j*50+35, 51, 51);
					this.add(wall);
				}else if(datas[i][j] == 5) {
					JLabel wall = new JLabel(new ImageIcon("wall-4.png"));
					wall.setBounds(i*50+25, j*50+35, 51, 51);
					this.add(wall);
				}else if(datas[i][j] == -1) {//Ŀ��ĳ�ʼ��
									
					JLabel Aim = new JLabel(new ImageIcon("aim.png"));
					Aim.setBounds(i*50+25, j*50+35, 51, 51);
					this.add(Aim);
					Score--;

				}
					//�ذ�ĳ�ʼ
				if (datas[i][j] == 0 || datas[i][j] == 9 || datas[i][j] == -1) {
					if ((i+j)%2 == 0) {
						JLabel lab_floor = new JLabel(new ImageIcon("floor1.png"));
						lab_floor.setBounds(i*50+25, j*50+35, 51, 51);
						this.add(lab_floor);
					}else {
						JLabel lab_floor = new JLabel(new ImageIcon("floor2.png"));
						lab_floor.setBounds(i*50+25, j*50+35, 51, 51);
						this.add(lab_floor);
					}
				}
			}
		}
	}
	private void HumanInit() {
		//ʹ��Jlabel�齨��ģ������
		lab_Human = new JLabel(new ImageIcon("human-front.png"));
		lab_Human.setBounds(25+50*Human_X, 35+50*Human_Y, 50, 50); 
		this.add(lab_Human);
	}
	//���ӳ�ʼ��
	private void BoxInit(int[][] box) {
		for(int i = 0; i < box.length; i++) {
			for(int j = 0; j < box[i].length; j++)
				if( datas[i][j] == 9) {
					JLabel lab_Box = new JLabel(new ImageIcon("box-no.png"));
					lab_Box.setBounds(i*50+25, j*50+35, 50, 50);
					lab_Boxs[i][j] = lab_Box;
					datas[i][j] = 0;
					this.add(lab_Boxs[i][j]);
				}
			}
		}
	//������ʼ��
	private void BackGroundInit() {
		//����һ��ͼƬ����
		Icon i = new ImageIcon("bg.png");
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
		//���ô��ڱ���
		this.setTitle("������ v1.0");
		//���ô���λ��
		this.setLocation(350, 100);
		//setSize�������ô��ڴ�С
		this.setSize(800, 600);
		//setVisible�������ô����Ƿ�ɼ�
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
		if(key == 37 && datas[Human_X-1][Human_Y] <= 0) {//��


			if(lab_Boxs[Human_X-1][Human_Y] != null && lab_Boxs[Human_X-2][Human_Y] == null && datas[Human_X-2][Human_Y] <= 0) {
				lab_Boxs[Human_X-1][Human_Y].setLocation((Human_X -2)*50+25,(Human_Y)*50+35);
				lab_Boxs[Human_X-2][Human_Y] = lab_Boxs[Human_X-1][Human_Y];
				lab_Boxs[Human_X-1][Human_Y] = null;
				Human_X--;
				lab_Human.setIcon(new ImageIcon("human-left.png"));			
				lab_Human.setLocation(x -50, y);
				//�˴����ж����ӱ�ɫ����
				if(datas[Human_X][Human_Y] == 0 && datas[Human_X-1][Human_Y] == -1) {
					lab_Boxs[Human_X-1][Human_Y].setIcon(new ImageIcon("box-yes.png"));
					Score++;
				}else if(datas[Human_X][Human_Y] == -1 && datas[Human_X-1][Human_Y] == 0){
					lab_Boxs[Human_X-1][Human_Y].setIcon(new ImageIcon("box-no.png"));
					Score--;
				}
			}
			if(lab_Boxs[Human_X-1][Human_Y] == null) {
				Human_X--;
				lab_Human.setIcon(new ImageIcon("human-left.png"));			
				lab_Human.setLocation(x -50, y);
			}
		}else if (key == 38 && datas[Human_X][Human_Y-1] <= 0) {//��
			
			if(lab_Boxs[Human_X][Human_Y-1] != null && lab_Boxs[Human_X][Human_Y-2] == null && datas[Human_X][Human_Y-2] <= 0) {
				lab_Boxs[Human_X][Human_Y-1].setLocation((Human_X )*50+25,(Human_Y-2)*50+35);
				lab_Boxs[Human_X][Human_Y-2] = lab_Boxs[Human_X][Human_Y-1];
				lab_Boxs[Human_X][Human_Y-1] = null;
				Human_Y--;
				lab_Human.setIcon(new ImageIcon("human-above.png"));			
				lab_Human.setLocation(x, y-50);
				//�˴����ж����ӱ�ɫ����
				if(datas[Human_X][Human_Y] == 0 && datas[Human_X][Human_Y-1] == -1) {
					lab_Boxs[Human_X][Human_Y-1].setIcon(new ImageIcon("box-yes.png"));
					Score++;
				}else if(datas[Human_X][Human_Y] == -1 && datas[Human_X][Human_Y-1] == 0){
					lab_Boxs[Human_X][Human_Y-1].setIcon(new ImageIcon("box-no.png"));
					Score--;
				}
			}
			if(lab_Boxs[Human_X][Human_Y-1] == null) {
				Human_Y--;
				lab_Human.setIcon(new ImageIcon("human-above.png"));			
				lab_Human.setLocation(x, y-50);
			}
		}else if (key == 39 && datas[Human_X+1][Human_Y] <= 0) {//��
			
			if(lab_Boxs[Human_X+1][Human_Y] != null && lab_Boxs[Human_X+2][Human_Y] == null && datas[Human_X+2][Human_Y] <= 0) {
				lab_Boxs[Human_X+1][Human_Y].setLocation((Human_X +2)*50+25,(Human_Y)*50+35);
				lab_Boxs[Human_X+2][Human_Y] = lab_Boxs[Human_X+1][Human_Y];
				lab_Boxs[Human_X+1][Human_Y] = null;
				Human_X++;
				lab_Human.setIcon(new ImageIcon("human-right.png"));			
				lab_Human.setLocation(x +50, y);
				//�˴����ж����ӱ�ɫ����
				if(datas[Human_X][Human_Y] == 0 && datas[Human_X+1][Human_Y] == -1) {
					lab_Boxs[Human_X+1][Human_Y].setIcon(new ImageIcon("box-yes.png"));
					Score++;
				}else if(datas[Human_X][Human_Y] == -1 && datas[Human_X+1][Human_Y] == 0){
					lab_Boxs[Human_X+1][Human_Y].setIcon(new ImageIcon("box-no.png"));
					Score--;
				}
			}
			if(lab_Boxs[Human_X+1][Human_Y] == null) {
				Human_X++;
				lab_Human.setIcon(new ImageIcon("human-right.png"));			
				lab_Human.setLocation(x +50, y);
			}
		}else if (key == 40 && datas[Human_X][Human_Y+1] <= 0) {//��
			
			if(lab_Boxs[Human_X][Human_Y+1] != null && lab_Boxs[Human_X][Human_Y+2] == null && datas[Human_X][Human_Y+2] <= 0) {
				lab_Boxs[Human_X][Human_Y+1].setLocation((Human_X )*50+25,(Human_Y+2)*50+35);
				lab_Boxs[Human_X][Human_Y+2] = lab_Boxs[Human_X][Human_Y+1];
				lab_Boxs[Human_X][Human_Y+1] = null;
				Human_Y++;
				lab_Human.setIcon(new ImageIcon("human-front.png"));			
				lab_Human.setLocation(x, y+50);
				//�˴����ж����ӱ�ɫ����
				if(datas[Human_X][Human_Y] == 0 && datas[Human_X][Human_Y+1] == -1) {
					lab_Boxs[Human_X][Human_Y+1].setIcon(new ImageIcon("box-yes.png"));
					Score++;
				}else if(datas[Human_X][Human_Y] == -1 && datas[Human_X][Human_Y+1] == 0){
					lab_Boxs[Human_X][Human_Y+1].setIcon(new ImageIcon("box-no.png"));
					Score--;
				}
			}
			if(lab_Boxs[Human_X][Human_Y+1] == null) {
				Human_Y++;
				lab_Human.setIcon(new ImageIcon("human-front.png"));			
				lab_Human.setLocation(x, y+50);
			}
		}
		if(Score == 0) {
			JOptionPane.showMessageDialog(null, "��Ϸʤ��", "��ʾ",JOptionPane.WARNING_MESSAGE);  
		}
		
	}

	@Override
	public void keyReleased(KeyEvent a) {
		//System.out.println("song");
		
	}
	@Override
	public void keyTyped(KeyEvent a) {
		
	}
}
