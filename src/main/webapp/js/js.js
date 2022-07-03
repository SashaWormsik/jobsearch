function clickFormEvent(i) {
    let formWorker = document.getElementById('worker');
    let formCompany = document.getElementById('company');
    let buttonWorker = document.getElementById('buttonWorker');
    let buttonCompany = document.getElementById('buttonCompany');

    if (i === 'worker') {
        formWorker.style.display = 'block';
        buttonWorker.style.display = 'block'
        formCompany.style.display = 'none';
        buttonCompany.style.display = 'none'
    } else {
        formWorker.style.display = 'none';
        buttonWorker.style.display = 'none'
        formCompany.style.display = 'block';
        buttonCompany.style.display = 'block'
    }
}

let modalOne = document.getElementById('id01');
let modalTwo = document.getElementById('id02');

window.onclick = function (event) {
    if (event.target === modalOne) {
        modalOne.style.display = "none";
    }
    if (event.target === modalTwo) {
        modalTwo.style.display = "none";
    }
}

document.getElementById('idD').min = new Date(Date.now() + (3600 * 1000 * 24)).toISOString().slice(0, 16);
document.getElementById('idD').value = new Date(Date.now() + (3600 * 1000 * 24)).toISOString().slice(0, 16);


