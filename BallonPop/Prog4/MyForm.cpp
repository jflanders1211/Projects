#include "MyForm.h"

using namespace System;
using namespace System::Windows::Forms;
[STAThread]
int main(cli::array<String^>^ args) {
   Application::EnableVisualStyles();
   Application::SetCompatibleTextRenderingDefault(false);
   Prog4::MyForm form;
   Application::Run(% form);
}