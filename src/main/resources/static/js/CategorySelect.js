    		let category_Id = document.getElementById('categoryId').value;

	function radioCheck(categoryId){
		category_Id= categoryId;
		console.log("category_Id : "+category_Id);
	}



flag = 1;
function closeAndSendParam()
{ if (flag==1) { open("/board/select/step_4?categoryId="+category_Id , "_blank"); self.close(); } }



	function setChildText(){

		let popupWidth = 500;
		let popupHeight = 400;
		let left = Math.ceil((window.screen.width-popupWidth)/2);
		let top = Math.ceil((window.screen.height-popupHeight)/2);;
		console.log("choose : "+choose);

		let pickup_id = document.getElementById('place_name').value;
		let url = "/board/select/step_4?categoryId="+choose;
		openWin = window.open(url,"parentForm2","width="+popupWidth+",height="+popupHeight+",left="+left+",top="+top+"resizable=yes")

	}