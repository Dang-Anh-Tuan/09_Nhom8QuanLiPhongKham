tail.select("#select-medicine", {
	search: true,
	deselect: true,
});

tail.select("#select-method-use", {});

const tableList = document.querySelector(".table-list");
const inputNameMedicine = document.querySelector("#select-medicine");
const inputAmount = document.querySelector("#amount");
const inputMethodUse = document.querySelector("#select-method-use");
let listDetailThuoc = [];
document
	.querySelector("#btn-add-medicine")
	.addEventListener("click", function() {
		let div = document.createElement("div");

		listDetailThuoc.push({
			idThuoc:
				+inputNameMedicine.options[inputNameMedicine.selectedIndex].value,
			lieuDung: +inputAmount.value,
			cachDung: inputMethodUse.options[inputMethodUse.selectedIndex].text,
		});

		let nameMedicine =
			inputNameMedicine.options[inputNameMedicine.selectedIndex].text;
		let amount = inputAmount.value;
		let methodUse = inputMethodUse.options[inputMethodUse.selectedIndex].text;

		let html = `
    <div class="grid__col-lg-1 table-list__cell">${listDetailThuoc.length}</div>
    <div class="grid__col-lg-3 table-list__cell">${nameMedicine}</div>
    <div class="grid__col-lg-2 table-list__cell">${amount}</div>
    <div class="grid__col-lg-3 table-list__cell">${methodUse}</div>
    `;
		div.innerHTML = html;
		div.className =
			"grid__row grid__row--spbw grid__row--item-center table-list__row";
		tableList.append(div);

		console.log(listDetailThuoc);
	});

const idBenhAn = window.location.href.split("/").at(-1);
const btnCreateDonThuoc = document.querySelector("#btn-create-donthuoc");
const url = "http://localhost:8080/bacsi/kedon/" + idBenhAn;

(function start() {
	handleCreateBtn();
})();

function handleCreateBtn() {
	btnCreateDonThuoc.onclick = function() {
		handleCreateDonThuoc(listDetailThuoc);
	};
}


function handleCreateDonThuoc(listDetailThuoc) {
	const option = {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(listDetailThuoc)
	};
	fetch(url, option)
		.then(function(response) {
			return response.json();
		})
		.then(function(result) {
			console.log(result);
		})
		.catch(function(err) {
			console.log(err);
		});
}