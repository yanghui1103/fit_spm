//���嶨�����ͱ���
var ORDER_TYPE_NEW 				= "1";  	//��װ
var ORDER_TYPE_CHANGENUM 		= '2'; 		//�ĺ�
var ORDER_TYPE_REMOVE    		= '3'; 		//���
var ORDER_TYPE_CANCEL			= '4'; 		//����
var ORDER_TYPE_CHANGECUST		= '5'; 		//�޸Ŀͻ���Ϣ
var ORDER_TYPE_CHANGEACCT		= '6'; 		//�޸��ʻ���Ϣ
var ORDER_TYPE_CHANGENWK		= '7'; 		//�޸ĳ̿���Ϣ
var ORDER_TYPE_JOINCENTREX		= '8'; 		//��������
var ORDER_TYPE_QUITCENTREX		= '9'; 		//��������
var ORDER_TYPE_CREATECENTREX	= '10';		//��������
var ORDER_TYPE_STEPOTHERCUST	= '11';		//����
var ORDER_TYPE_CHANGEPAYRELA	= '12';		//�޸ĸ��ѹ�ϵ
var ORDER_TYPE_CHANGEPAYMETHOD	= '13';		//�޸�֧����ʽ
var ORDER_TYPE_PATCHPHS			= '14';		//С��ͨ����
var ORDER_TYPE_CHANGEPHS		= '15';		//С��ͨ�ַ�����
var ORDER_TYPE_CHANGENAME		= '16';		//����
var ORDER_TYPE_CHANGEFEE		= '17';		//�ʷѱ��
var ORDER_TYPE_CHANGEPASSWORD	= '18';		//�Ŀͻ����롢��Ʒ����
var ORDER_TYPE_STOPOBLIGATENUM	= '19';		//�û�����ͣ������
var ORDER_TYPE_OPENNUM			= '20';		//�û����븴��
var ORDER_TYPE_OPENPROMPT		= '21';		//����֪ͨ��
var ORDER_MODIFY				= '63';     //�����޸�
var ORDER_STOP					= '46';		//��ͣ����
var ORDER_CONTINUE				= '47';		//�ָ�����

//��������
var DATA_TYPE_STRING			= '1';		//�ַ���
var DATA_TYPE_BOOLEAN			= '2';		//������
var DATA_TYPE_INT				= '3';		//����
var DATA_TYPE_DATE				= '4';		//������
var DATA_TYPE_FLOAT				= '5';		//������
var DATA_TYPE_PHONE				= '6';		//�绰����
var DATA_TYPE_EMAIL				= '7';		//��������
var DATA_TYPE_PASSWORD			= '8';		//����

/*����ƷĿ¼����*/

/*�̶��绰������Ʒ*/
var PUBLIC_PHONE                = '10';     //���õ绰
var TRUNK_LINE                  = '11';     //�м�
var SPECIAL_TELE_LINE           = '12';     //ģ��ר��
var ORDINARY_PHONE              = '15';     //��ͨ�绰
/*�����л�������Ʒ*/
var WIRELESS_PHONE              = '14';     //С��ͨ

//�̿ع��ܹ�ϵ
var NWK_RELA_LEAN				= 2;      //����
var NWK_RELA_MUTEX				= 1;      //����