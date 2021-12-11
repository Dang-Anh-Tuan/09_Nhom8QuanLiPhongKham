const idBenhAn = window.location.href.split("/").at(-1);
const btnKhoiBenh = document.querySelector(".btn-khoi-benh");
const url = "http://localhost:8080/bacsi/xembenhan/" + idBenhAn;
const idBenh = btnKhoiBenh.id;
const data = { id: +idBenh };

(function start() {
	handleKhoiBtn()
})();

function handleKhoiBtn() {
	btnKhoiBenh.onclick = function() {
		console.log("abc");
		handleKhoibenh(data)
	};
}


function handleKhoibenh(data) {
	const option = {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(data)
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
