// Common.js

// newFunction
function ProdSpec(){
   var index;
   var prodInstanceId;
   var prodInstanceName;
   var tagProdSpecId;
   var prodSpecId;
   var prodNumber ;
   var areaId;
   var orderType;
   var custId;
   var custName;
   var coId;
   var isComp;
   var priority;
   var flag;    //��ʶ��ҵ���Ƿ���Ҫ������ϵ��,��ϵ�绰����Ϣ
   var prodStatusCd;   //��Ʒ״̬ID
   var actionType;
   var actionTypeOld;//�����޸�ʱ��Ҫʹ��ԭ�еĶ������͹�����
   var offeringId;
   var coTypeName;
   var categoryNodeId;
   var actionId;   //��ϲ�Ʒ��1:��װ2:�˳�������3:�����ɫ
   var lockDom;
   var oldOrderType; //�����޸�ʱ��Ҫ��ʾԭ�еĶ������͹�����
   
   //δ����������
   //var coId,  ����
   var coNbr;
   var state;
   //var prospecId, ����
   //var custId,  ����
   //var areaId,  ����
   var acessNumber;
   var offferingName;
   //var orderType;  ����
   var orderTypeName;
   var reasonCd;  //�˵�ʱ��ÿһ�Ŷ�����ԭ��cd����|����
}

//��Ա��Ʒ�������
function CompProd(){   
   var accessNum;
   var memberTypeId;  //1.��װ,2.���в�Ʒ,3.���ж���
   var roleName;
   var memberId;
   var prodId;
   var prodSpecId;
   var prodName;
   var prodSpecName;
   var offeringName;
   var offeringId;
   var newOfferingName;
   var newOfferingId;
   var num;
   var partyId;
   var partyName;
   var roleId;
   var actionId;    //1.����,2.�˳�,3.���
   var orderType;  //��������ID
   var orderTypeId;  //��������ID
   var orderTypeName;
   var areaId;
   var priority;
   var flag;    //��ʶ��ҵ���Ƿ���Ҫ������ϵ��,��ϵ�绰����Ϣ
   var actionTypeCd;   //��������
   var isAdd;//�Ƿ���װ,Y:��װ���룬N���ϳ�Ա��O���ϲ�Ʒ����
}

//����ת������
function DataTransferObj(){
    var preCoId;
    var prodSpecId;
    var prodName;
    var prodSpecName;
    var offeringName;
    var roleId;
    var roleName;
    var accessNum;
    var memberId;
    var memberTypeName;
    var actionId;
    var memberTypeId;
    var orderType;
    var orderTypeId;
    var orderTypeName;
    var actionTypeCd;   //��������
    var partyId;
    var partyName;
    var areaId;
    var priority;
    var flag;    //��ʶ��ҵ���Ƿ���Ҫ������ϵ��,��ϵ�绰����Ϣ
    var isAdd;//�Ƿ���װ,Y:��װ���룬N���ϳ�Ա��O���ϲ�Ʒ����
    var offeringId;
    var newOfferingName;
    var newOfferingId;
}

//ADSL��װ�绰������ѡ��ʱ,��ǰ���ж����Ĳ�����Ϣ����
function ExistOrderData(){
    var prodSpecId;   
    var areaId;     //��ѯ���ò�Ʒʱ�����
    var areaName;   //��ѯ���ò�Ʒʱ�����
    var prodSpecName;
    var accessNbr;   //�������
    var anId;   //���������ID
    var tagProdSpecId;   //Ŀ���Ʒ���ID
    var prodId;      //��ƷID(����coId)
    var addrId;
    var addrDetail;
    var tmlId;    //����ID
    var anTypeCd;  //ѡ������
    var channelId;  //����ID
    var index;  //��ǰ�������к�
}

function custMessage(){
        //other
	var partyId; //0
	var partyName;//1
	var defaultIdentityId;//2
	var defaultIdentityNumber;//3
	var defaultIdentityName;//12
	var radioSingelId;//22
	var areaId ;//6
	var areaName;//7
	var addressStr;//8
	var mailAddressStr;//9
	var simpleSpell;//10
	var partyTypeName;//11
	var partyTypeSelectIndex;//16
	var partyTypeCd;//25
	var partyStatusCd;//26
	var partyStatusSelectIndex;//27
	var segmentSelectIndex;//17
	var segmentName;//13
	var segmentId;//28
	var mailAddressId;//14
	var addressId ;//15
	var industryTypeId;//18
	var industryTypeName;//19
	var professionCd;//20
	var professionName;//21
	var passwordSearch;//23
	var passwordBussiness;//24
	var profileArray = new Array();	
	var identityArray = new Array();
}

function profile(){
	var textValue;//�ύ��
	var CatgCdScript;//�ύ��
	var id;//�ؼ�idhidden  text ��
	var profileName;
}

function identity(){
	var identityCd;//id
	var identityNumber;//value
	var identityName;//name
}