$(document).ready(function() {
 
    $('#listeCom').dataTable( {
        'bProcessing': false,
        'bServerSide': false,
        'sAjaxSource': './MedocDataServlet',
        'bJQueryUI': true,
        columns: [
            { "data": 'denominationMedicament' },
            { "data": 'formePharmaceutique' },
            { "data": 'voieAdministration' },
            { "data": 'titulaire' },
            { "data": 'link' }
        ],
        "language": {
            "url": "French.json"
        }
    } ); 
    
    $('#listeMed').dataTable( {
        'bProcessing': false,
        'bServerSide': false,
        'sAjaxSource': './DataTableInfoServlet',
        'bJQueryUI': true,
        columns: [
            { "data": 'denominationMedicament' },
            { "data": 'formePharmaceutique' },
            { "data": 'voieAdministration' },
            { "data": 'statutAdministratifAmm' },
            { "data": 'titulaire' },
            { "data": 'dateAmm' },
            { "data": 'link2' }
        ],
        "language": {
            "url": "French.json"
        }
    } );
    
    $('#listeMedAvisLien').dataTable( {
        'bProcessing': false,
        'bServerSide': false,
        'sAjaxSource': './DataTableSMRServlet',
        'bJQueryUI': true,
        columns: [
            { "data": 'denominationMedicament' },
            { "data": 'formePharmaceutique' },
            { "data": 'voieAdministration' },
            { "data": 'statutAdministratifAmm' },
            { "data": 'titulaire' },
            { "data": 'dateAmm' },
            { "data": 'link3' }
        ],
        "language": {
            "url": "French.json"
        }
    } );
    
    $('#listeNonCom').dataTable( {
        'bProcessing': false,
        'bServerSide': false,
        'sAjaxSource': './MedocDataServlet2',
        'bJQueryUI': true,
        columns: [
            { "data": 'denominationMedicament' },
            { "data": 'formePharmaceutique' },
            { "data": 'voieAdministration' },
            { "data": 'statutAdministratifAmm' },
            { "data": 'titulaire' },
            { "data": 'dateAmm' },
            { "data": 'link' }
        ],
        "language": {
            "url": "French.json"
        }
    } );
    
    $('#listeCond').dataTable( {
        'bProcessing': false,
        'bServerSide': false,
        'sAjaxSource': './DataTableInfoServlet',
        'bJQueryUI': true,
        columns: [
        	 { "data": 'denominationMedicament' },
             { "data": 'formePharmaceutique' },
             { "data": 'voieAdministration' },
             { "data": 'statutAdministratifAmm' },
             { "data": 'titulaire' },
             { "data": 'dateAmm' },
            { "data": 'linka' }
        ],
        "language": {
            "url": "French.json"
        }
    } );
  
    $('#listeCompo').dataTable( {
        'bProcessing': false,
        'bServerSide': false,
        'sAjaxSource': './MedocDataServletCompo',
        'bJQueryUI': true,
        columns: [
            { "data": 'denominationSubstance' },
            { "data": 'natureComposant' },
            { "data": 'lien' }
        ],
        "language": {
            "url": "French.json"
        }
    } );
    
    $('#listeasmr').dataTable( {
        'bProcessing': false,
        'bServerSide': false,
        'sAjaxSource': './ASMRDataServlet',
        'bJQueryUI': true,
        columns: [
            { "data": 'denominationMedicament' },
            { "data": 'formePharmaceutique' },
            { "data": 'voieAdministration' },
            { "data": 'statutAdministratifAmm' },
            { "data": 'titulaire' },
            { "data": 'dateAmm' },
            { "data": 'link4' }
        ],
        "language": {
            "url": "French.json"
        }
    } );
    $('#listesmr').dataTable( {
        'bProcessing': false,
        'bServerSide': false,
        'sAjaxSource': './ASMRDataServlet',
        'bJQueryUI': true,
        columns: [
            { "data": 'denominationMedicament' },
            { "data": 'formePharmaceutique' },
            { "data": 'voieAdministration' },
            { "data": 'statutAdministratifAmm' },
            { "data": 'titulaire' },
            { "data": 'dateAmm' },
            { "data": 'link5' }
        ],
        "language": {
            "url": "French.json"
        }
    } );
    
    $('#listeMedCompo').dataTable( {
        'bProcessing': false,
        'bServerSide': false,
        'sAjaxSource': './MedocDataServletMedCompo',
        'bJQueryUI': true,
        columns: [
            { "data": 'denominationMedicament' },
            { "data": 'formePharmaceutique' },
            { "data": 'voieAdministration' },
            { "data": 'titulaire' },
            { "data": 'link' }
        ],
        "language": {
            "url": "French.json"
        }
    } ); 
    $('#listeGen').dataTable( {
        'bProcessing': false,
        'bServerSide': false,
        'sAjaxSource': './GroupeServlet',
        'bJQueryUI': true,
        columns: [
            { "data": 'libelleGroupeGenerique' },
            { "data": 'lien' }
        ],
        "language": {
            "url": "French.json"
        }
    } );
    
    $('#listeGenMed').dataTable( {
        'bProcessing': false,
        'bServerSide': false,
        'sAjaxSource': './GroupeServlet2',
        'bJQueryUI': true,
        columns: [
        	{ "data": 'denominationMedicament' },
            { "data": 'formePharmaceutique' },
            { "data": 'voieAdministration' },
            { "data": 'titulaire' }
        ],
        "language": {
            "url": "French.json"
        }
    } );
    $('#listeMessa').dataTable( {
        'bProcessing': false,
        'bServerSide': false,
        'sAjaxSource': './Sujet_Controller',
        'bJQueryUI': true,
        columns: [
            { "data": 'pseudoMessage' },
            { "data": 'dateMessage' },
            { "data": 'message' },
            { "data": 'lienk' },
        ],
        "language": {
            "url": "French.json"
        }
    } );
    $('#listeRep').dataTable( {
        'bProcessing': false,
        'bServerSide': false,
        'sAjaxSource': './ReponseController',
        'bJQueryUI': true,
        columns: [
        	 { "data": 'reponse' },
        	{ "data": 'dateReponse' },
        ],
        "language": {
            "url": "French.json"
        }
    } );
} );
function buttonCode(id) {
	document.getElementById("cis").value = id
	document.getElementById("codeForm").submit();
}
function buttonCodeCompo(id) {
	document.getElementById("code").value = id
	document.getElementById("codeForm").submit();
}
function buttonIdMess(id){
	document.getElementById("message").value = id;
	document.getElementById("codeForm").submit();
}

//ne filtre pas les accents lors d'une recherche
(function(){
	 
	function removeAccents ( data ) {
	    if ( data.normalize ) {
	        // Use I18n API if avaiable to split characters and accents, then remove
	        // the accents wholesale. Note that we use the original data as well as
	        // the new to allow for searching of either form.
	        return data +' '+ data
	            .normalize('NFD')
	            .replace(/[\u0300-\u036f]/g, '');
	    }
	 
	    return data;
	}
	 
	var searchType = jQuery.fn.DataTable.ext.type.search;
	 
	searchType.string = function ( data ) {
	    return ! data ?
	        '' :
	        typeof data === 'string' ?
	            removeAccents( data ) :
	            data;
	};
	 
	searchType.html = function ( data ) {
	    return ! data ?
	        '' :
	        typeof data === 'string' ?
	            removeAccents( data.replace( /<.*?>/g, '' ) ) :
	            data;
	};
	 
	}());