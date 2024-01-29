#pragma once
#include "MyObject.h"
#include "MyObjectList.h"
#include "GameObjects.h"
#include <iostream>
#include <cstdlib>

namespace Prog4 {

	using namespace System;
	using namespace System::ComponentModel;
	using namespace System::Collections;
	using namespace System::Windows::Forms;
	using namespace System::Data;
	using namespace System::Drawing;

	/// <summary>
	/// Summary for MyForm
	/// </summary>
	public ref class MyForm : public System::Windows::Forms::Form
	{
	public:
		MyForm(void)
		{
			InitializeComponent();
			myObjectList = nullptr;
			//
			//TODO: Add the constructor code here
			//
		}

	protected:
		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		~MyForm()
		{
			if (components)
			{
				delete components;
			}
		}
	private: MyObjectList* myObjectList;
	private: System::Windows::Forms::TextBox^ numOfBalloons;

	protected:
	private: System::Windows::Forms::Button^ button1;
	private: System::Windows::Forms::Button^ button2;
	private: System::Windows::Forms::TrackBar^ trackBar1;
	private: System::Windows::Forms::Label^ label1;
	private: System::Windows::Forms::Label^ label2;
	private: System::Windows::Forms::TextBox^ score;



	private: System::Windows::Forms::Timer^ timer1;
	private: System::Windows::Forms::Panel^ panel1;


	private: System::ComponentModel::IContainer^ components;

	private:
		/// <summary>
		/// Required designer variable.
		/// </summary>


#pragma region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		void InitializeComponent(void)
		{
			this->components = (gcnew System::ComponentModel::Container());
			this->numOfBalloons = (gcnew System::Windows::Forms::TextBox());
			this->button1 = (gcnew System::Windows::Forms::Button());
			this->button2 = (gcnew System::Windows::Forms::Button());
			this->trackBar1 = (gcnew System::Windows::Forms::TrackBar());
			this->label1 = (gcnew System::Windows::Forms::Label());
			this->label2 = (gcnew System::Windows::Forms::Label());
			this->score = (gcnew System::Windows::Forms::TextBox());
			this->timer1 = (gcnew System::Windows::Forms::Timer(this->components));
			this->panel1 = (gcnew System::Windows::Forms::Panel());
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->trackBar1))->BeginInit();
			this->SuspendLayout();
			// 
			// numOfBalloons
			// 
			this->numOfBalloons->Location = System::Drawing::Point(16, 3);
			this->numOfBalloons->Name = L"numOfBalloons";
			this->numOfBalloons->Size = System::Drawing::Size(99, 22);
			this->numOfBalloons->TabIndex = 0;
			this->numOfBalloons->Text = L"0";
			// 
			// button1
			// 
			this->button1->Location = System::Drawing::Point(16, 27);
			this->button1->Name = L"button1";
			this->button1->Size = System::Drawing::Size(98, 31);
			this->button1->TabIndex = 1;
			this->button1->Text = L"Balloon rush";
			this->button1->UseVisualStyleBackColor = true;
			this->button1->MouseDown += gcnew System::Windows::Forms::MouseEventHandler(this, &MyForm::button1_MouseDown);
			// 
			// button2
			// 
			this->button2->Location = System::Drawing::Point(129, 13);
			this->button2->Name = L"button2";
			this->button2->Size = System::Drawing::Size(122, 59);
			this->button2->TabIndex = 2;
			this->button2->Text = L"Fire";
			this->button2->UseVisualStyleBackColor = true;
			this->button2->MouseDown += gcnew System::Windows::Forms::MouseEventHandler(this, &MyForm::button2_MouseDown);
			// 
			// trackBar1
			// 
			this->trackBar1->Location = System::Drawing::Point(266, 41);
			this->trackBar1->Name = L"trackBar1";
			this->trackBar1->Size = System::Drawing::Size(125, 56);
			this->trackBar1->TabIndex = 3;
			this->trackBar1->Scroll += gcnew System::EventHandler(this, &MyForm::trackBar1_Scroll);
			// 
			// label1
			// 
			this->label1->AutoSize = true;
			this->label1->Location = System::Drawing::Point(304, 21);
			this->label1->Name = L"label1";
			this->label1->Size = System::Drawing::Size(49, 17);
			this->label1->TabIndex = 4;
			this->label1->Text = L"Speed";
			// 
			// label2
			// 
			this->label2->AutoSize = true;
			this->label2->Location = System::Drawing::Point(424, 41);
			this->label2->Name = L"label2";
			this->label2->Size = System::Drawing::Size(45, 17);
			this->label2->TabIndex = 5;
			this->label2->Text = L"Score";
			// 
			// score
			// 
			this->score->Location = System::Drawing::Point(475, 41);
			this->score->Name = L"score";
			this->score->Size = System::Drawing::Size(117, 22);
			this->score->TabIndex = 6;
			this->score->Text = L"0";
			// 
			// timer1
			// 
			this->timer1->Interval = 50;
			this->timer1->Tick += gcnew System::EventHandler(this, &MyForm::timer1_Tick);
			// 
			// panel1
			// 
			this->panel1->Location = System::Drawing::Point(16, 97);
			this->panel1->Name = L"panel1";
			this->panel1->Size = System::Drawing::Size(978, 612);
			this->panel1->TabIndex = 7;
			// 
			// MyForm
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(8, 16);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->ClientSize = System::Drawing::Size(1006, 721);
			this->Controls->Add(this->panel1);
			this->Controls->Add(this->score);
			this->Controls->Add(this->label2);
			this->Controls->Add(this->label1);
			this->Controls->Add(this->trackBar1);
			this->Controls->Add(this->button2);
			this->Controls->Add(this->button1);
			this->Controls->Add(this->numOfBalloons);
			this->Name = L"MyForm";
			this->Text = L"BallonBattle";
			this->FormClosed += gcnew System::Windows::Forms::FormClosedEventHandler(this, &MyForm::MyForm_FormClosed);
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->trackBar1))->EndInit();
			this->ResumeLayout(false);
			this->PerformLayout();

		}
#pragma endregion
// Event handler for balloon rush
private: System::Void button1_MouseDown(System::Object^ sender, System::Windows::Forms::MouseEventArgs^ e) {

	// Checks if an object of MyObjectList is already created, if not it makes one
	if (myObjectList == nullptr)
		myObjectList = new MyObjectList();

	// Variable for the X-Coordinate of an object
	int X;

	// Converting whats in the textbox to an int
	int value = Convert::ToInt32(numOfBalloons->Text);

	// Checks textbox to see how many balloons need to be made
	// My code requires an int in the textbox otherwise it wont make any balloons
	if (value >= 0)
	{
		// Loop to the number in the textbox
		for (int i = 0; i < value; i++)
		{
			// Creating a random number to determine if the balloon object
			// is going to be red or yellow
			int num = rand() % 2 + 1;

			if (num == 1)
			{
				// Creating a red balloon and adding it to the list
				MyObject* redBalloon = new RedBalloon(X = myObjectList->RandomizedX(), 600, panel1, score);
				myObjectList->Add(redBalloon);
			}
			else
			{
				// Creating a yellow balloon and adding it to the list
				MyObject* yellowBalloon = new YellowBalloon(X = myObjectList->RandomizedX(), 600, panel1, score);
				myObjectList->Add(yellowBalloon);
			}
		}
	}

	timer1->Enabled = true;
}

// Event handler for using the trackbar
private: System::Void trackBar1_Scroll(System::Object^ sender, System::EventArgs^ e) {

	// Change speed of objects if trackBar is scrolled
	timer1->Interval = trackBar1->Value;

	// Sets speed back to normal if scrolled back to starting position
	if (trackBar1->Value == 0)
		timer1->Interval = 10;
}

// Event handler for the timer ticking
private: System::Void timer1_Tick(System::Object^ sender, System::EventArgs^ e) {

	// Clearing the panel
	panel1->Refresh();

	// Calling the method to move all the objects in the list
	myObjectList->MoveObjects();	

	// Checks all objects for collisions and deletes them
	myObjectList->objCollisions();
}

// Event handler for the fire button, bomb
private: System::Void button2_MouseDown(System::Object^ sender, System::Windows::Forms::MouseEventArgs^ e) {

	// Checks if an object of MyObjectList is already created, if not it makes one
	if (myObjectList == nullptr)
		myObjectList = new MyObjectList();

	// Creating a bomb object at a set spot
	MyObject* bomb = new Bomb(50, 50, panel1, score);
	myObjectList->Add(bomb);

	timer1->Enabled = true;
}

// Event handler for closing the program
private: System::Void MyForm_FormClosed(System::Object^ sender, System::Windows::Forms::FormClosedEventArgs^ e) {
	timer1->Enabled = false;
	delete myObjectList;
}
};
}

