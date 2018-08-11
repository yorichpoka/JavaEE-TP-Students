
// -- Variables -- //
var format_date = 'DD/MM/YYYY';

// -- Définir un cookies -- //
function dsSetCookie(cookie_name, cookie_value, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toUTCString();
    document.cookie = cookie_name + "=" + cookie_value + ";" + expires + ";path=/";
}

// -- Réccupérer la valeur d'un cookie -- //
function dsGetCookie(cookie_name) {
    var name = cookie_name + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

// -- Réccupérer la valeur de l'attribut d'un cookie -- //
function dsGetCookieAttribut(cookie_name, cookie_attribut) {
    // -- Réccupérer la valuer du cookie -- //
    var cookie_value = dsGetCookie(cookie_name);

    for (var i = 0; i < cookie_value.split('&').length; i++) {
        if (cookie_value.split('&')[i].split('=')[0] == cookie_attribut) {
            // -- Retourner la valeur de l'attribut -- //
            return cookie_value.split('&')[i].split('=')[1];
        }
    }
    // -- Retourner une chaine vide -- //
    return "";
}

// -- Mettre à jour l'attribut d'un cookie -- //
function dsSetCookieAttribut(cookie_name, cookie_attribut, valeur) {
    // -- Réccupérer la valuer du cookie -- //
    var cookie_value = dsGetCookie(cookie_name);
    // -- Dit oui ou non si un attribut a été trouvé -- //
    var existe = false;
    for (var i = 0; i < cookie_value.split('&').length; i++) {
        if (cookie_value.split('&')[i].split('=')[0] == cookie_attribut) {
            cookie_value = cookie_value.replace(cookie_attribut + '=' + cookie_value.split('&')[i].split('=')[1], cookie_attribut + '=' + valeur);
            existe = true;
            // -- Sortir de la boucle -- //
            break;
        }
    }
    // -- Si aucun attribut n'a été trouvé alors ajouter en un -- //
    if (!existe) {
        cookie_value = cookie_value + '&' + cookie_attribut + '=' + valeur;
    }
    // -- Mettre à jour la valeur du cookie -- //
    dsSetCookie(cookie_name, cookie_value, 1);
}

// -- Message box de notification -- //
function dsMessage_Box(type, message) {
    // -- Mise à jour de la taille -- //
    $('#modal_message_taille').removeClass('modal-dialog');
    $('#modal_message_taille').addClass('modal-dialog modal-sm');
    // -- Définir l'entête -- //
    $('#modal_message_titre').html('<i class="glyphicon glyphicon-info-sign text-' + type + '"></i> Information');
    // -- Definir le message -- //
    $('#modal_message_text').html(message);
    // -- Afficher -- //
    $('#modal_message').modal('show');
}
function dsMessage_Box_MD(type, message) {
    // -- Mise à jour de la taille -- //
    $('#modal_message_taille').removeClass('modal-dialog modal-sm');
    $('#modal_message_taille').addClass('modal-dialog');
    // -- Définir l'entête -- //
    $('#modal_message_titre').html('<i class="glyphicon glyphicon-info-sign text-' + type + '"></i> Information');
    // -- Definir le message -- //
    $('#modal_message_text').html(message);
    // -- Afficher -- //
    $('#modal_message').modal('show');
}

// -- Message notification -- //
function dsNotification(type, titre, message) {
    // -- Notifier -- //
    // -- Temp -- //
    //new PNotify({
    //    title: titre == null ? 'Information'
    //                         : titre,
    //    text: message,
    //    type: (type == 0) ? 'dark'
    //                      : (type == 1) ? 'success'
    //                                    : (type == 2) ? 'info'
    //                                                  : 'error'
    //});
}

// -- Jeter le telechargement d'un fichier -- //
function dsTelecharger_Fichier(fichierBase64, intitule) {
    try {
        // -- Construction de l'objet blob à telecharger -- //
        var blob = new Blob(
                        // -- COnvertir et charger le chier -- //
                        [dsConvert_Base64_En_ArrayBuffer(fichierBase64)],
                        // -- Définir le type du fichier -- //
                        { type: 'application/pdf' }
                    );
        // -- générer le telechargement du fichier -- //
        saveAs(blob, intitule);
    } catch (ex) {
        // -- Log -- //
        dsConsole('Méthode: Telecharger_Fichier, Exception: ' + ex.message);
        // -- Afficher le message d'erreur -- //
        dsMessage_Box('danger', 'Une erreur est survenue lors du téléchargement du fichier');
    }
}

// -- COnvertir base64 en tableau de byte -- //
function dsConvert_Base64_En_ArrayBuffer(base64) {
    try {
        var binaryString = window.atob(base64);
        var binaryLen = binaryString.length;
        var bytes = new Uint8Array(binaryLen);

        // -- Parcourir et lister -- //
        for (var i = 0; i < binaryLen; i++) {
            var ascii = binaryString.charCodeAt(i);
            bytes[i] = ascii;
        }

        // -- Renvoyer les bytes -- //
        return bytes;
    } catch (ex) {
        // -- Log -- //
        dsConsole('Méthode: iConvert_Base64_En_ArrayBuffer, Exception: ' + ex.message);
    }

    // -- Retourner Null en cas d'echec de convertion -- //
    return null;
}

// -- Afficher/Cacher l'etat de chargement de la page -- //
function dsAfficher_Page_Chargement(afficher, id_bouton) {
    if (afficher) {
        // -- Actualiser le bouton -- //
        if (id_bouton != null) {
            //$('#' + id_bouton).button('loading');
        }
        // -- Affichier le progress bar -- //
        NProgress.start();
        // -- Afficher le frame de chargelent -- //
        $('#frame_chargement').show();
    } else {
        // -- Actualiser le bouton -- //
        if (id_bouton != null) {
            //$('#' + id_bouton).button('reset');
        }
        // -- Finaliser le chargement du progress bar -- //
        NProgress.done();
        // -- Cacher le frae de chargement -- //
        $('#frame_chargement').hide();
    }
}

// -- Afficher/Cacher l'etat de chargement de la page -- //
function dsCacher_Page_Chargement(cacher) {
    if (cacher) {
        // -- Affichier le progress bar -- //
        NProgress.start();
        // -- Afficher le frame de chargelent -- //
        $('#frame_cacher').show();
    } else {
        // -- Finaliser le chargement du progress bar -- //
        NProgress.done();
        // -- Cacher le frae de chargement -- //
        $('#frame_cacher').hide();
    }
}

// -- Charger la localisation de l'utilisateur dans le cookiees -- //
function dsLocalisation() {
    try {
        // -- Teste si les cookiees n'ont pas encore été chargé -- //
        if (dsGetCookie('localisation') == '') {
            // -- Obtension de la localité de l'utilisateur -- //
            try {
                // -- Cacher/Afficher la page en chargement -- //
                dsCacher_Page_Chargement(true);
                // -- Réccupérer la localisation -- //
                $.getJSON("https://ipinfo.io/", function (result) {
                    // -- Enregistrer dans le cookiees -- //
                    dsSetCookie('localisation', JSON.stringify(result), 7);
                    // -- Cacher/Afficher la page en chargement -- //
                    dsCacher_Page_Chargement(false);
                });
            } catch (ex) {
                dsConsole(ex.message);
                // -- Enregistrer dans le cookiees -- //
                dsSetCookie('localisation', '', 7);
                // -- Cacher/Afficher la page en chargement -- //
                dsCacher_Page_Chargement(false);
            }
        }
    } catch (ex) {
        // -- Log -- //
        dsConsole('Méthode: dsLocalisation, Exception: ' + ex.message);
    }
}

// -- Redirection -- //
function dsHref(url) {
    window.location.href = url;
}

// -- Log -- //
function dsConsole(value) {
    console.log(value);
}

// -- Telecharger les conditions générale -- //
function dsTelechargerConditionGenerale(url) {

    // -- Afficher/Cacher l'etat de chargement de la page -- //
    dsAfficher_Page_Chargement(true, null);

    // -- Ajax -- //
    $.ajax({
        type: 'POST',
        url: '/DS/TelechargerCG',
        data: {
            url: url
        },
        success: function (resultat) {
            // -- Traiter en cas de réussite -- //
            if (!resultat.notification.est_echec) {
                // -- Telecharger le fichier -- //
                dsTelecharger_Fichier(
                    resultat.notification.donnee.fichier,
                    resultat.notification.donnee.libelle
                );
            }
            // -- Afficher/Cacher l'etat de chargement de la page -- //
            dsAfficher_Page_Chargement(false, null);
        },
        error: function () {
            // -- Afficher/Cacher l'etat de chargement de la page -- //
            dsAfficher_Page_Chargement(false, null);
            // -- Notifier -- //
            dsMessage_Box('danger', 'Une erreur est survenue');
        }
    });

}

// -- Comparer 2 dates -- //
function dsCompareDate(dateTimeA, dateTimeB) {
    var momentA = moment(dateTimeA, format_date);
    var momentB = moment(dateTimeB, format_date);

    if (momentA > momentB) return true;
    else return false;
}

// -- Mettre à jour le panel etape -- //
function dsPanelEtape(etape_hide, etape_show) {

    // -- Cacher l'image en cours -- //
    $("#img_etape_" + etape_hide).hide();
    $("#img_etape_" + etape_show).show();

    // -- Déclencher la soumissions -- //
    $('#a-tab-page-' + etape_show).trigger("click");

    // -- Mise à jour label bout on -- //
    $("#btn_suivant").html(
        ((etape_show === 2) ? 'VALIDER'
                            : (etape_show === 3) ? 'JE REGLE MA COTISATION'
                                                 : 'CONTINUER') + '    <i class="fa fa-angle-double-right"></i>'
    );

    // -- Mettre à jour la visibilité du bouton précédent -- //
    if (etape_show === 2 || etape_show === 3) {
        $("#btn_precedent").show(250);
    } else {
        $("#btn_precedent").hide(250);
    }

    // -- Mettre à jour la visibilité du bouton suivnat -- //
    if (etape_show === 3) {
        $("#btn_suivant").hide(250);
    } else {
        $("#btn_suivant").show(250);
    }

}
function dsPanelEtapePrelevementSEPA(etape_hide, etape_show) {
    
    // -- Mise à jour de la taille du modal -- //
    if (etape_show === 2) {
        $('#modal_prelevement_sepa_taille').removeClass('modal-dialog');
        $('#modal_prelevement_sepa_taille').addClass('modal-dialog modal-lg');
    }
    else {
        $('#modal_prelevement_sepa_taille').removeClass('modal-dialog modal-lg');
        $('#modal_prelevement_sepa_taille').addClass('modal-dialog');
    }

    // -- Cacher l'image en cours -- //
    $("#modal_prelevement_sepa_panel_" + etape_hide).hide();
    $("#modal_prelevement_sepa_panel_" + etape_show).show(250);

}

// -- Recharger les liste des assurés -- //
function dsRechargerListeAssure(nombre, etape) {

    // -- Ajax -- //
    $.ajax({
        type: 'GET',
        url: '/Souscription/Recharger_Liste_Assures',
        data: {
            nombre: nombre,
            etape: etape
        },
        error: function () {
            // -- Notifier -- //
            dsMessage_Box('danger', 'Une erreur est survenue');
            // -- Afficher/Cacher l'etat de chargement de la page -- //
            dsAfficher_Page_Chargement(false, null);
        }
    })
    .done(
        function (resultat) {
            // -- Mise à jour de la page -- //
            $("#div_liste_assure_etape_" + etape).html(resultat);
        }
    );

}

// -- Mise à jour des label souscripteur -- //
function dsChargerLabelSouscripteur(donnee) {

    // -- Définition du champ caché calcul -- //
    $('#calcul_tarif_effectue').val('1');

    $('#prime_commerciale').html(donnee.prime_commerciale);

    for (var i = 2; i <= 3; i++) {
        $('#recapitulatif_' + i + '_nombre_beneficiaire').html(donnee.souscripteur.ListeBeneficiaires.length);
        $('#recapitulatif_' + i + '_prime_commerciale').html(donnee.prime_commerciale);
        $('#recapitulatif_' + i + '_date_debut').html(donnee.date_debut);
        $('#recapitulatif_' + i + '_date_fin').html(donnee.date_fin);
        $('#recapitulatif_' + i + '_code_pays_depart').html(donnee.souscripteur.CodePays);
        $('#recapitulatif_' + i + '_code_pays_destination').html(donnee.code_pays_destination);
    }

    $('#recapitulatif_souscripteur_pays').val(donnee.pays);

    // -- Charger les région du souscripteur -- //
    //dsChargerRegion(donnee.souscripteur.CodePays);

}

// -- Charger les région du souscripteur -- //
function dsChargerRegion(pays) {

    // -- Ajax -- //
    $.ajax({
        type: "POST",
        url: '/Souscription/Charger_Region_Ville',
        data: {
            pays: pays
        },
        success: function (resultat) {
            // -- Traiter en cas de réussite -- //
            if (!resultat.notification.est_echec) {
                // -- Reset le combobox des villes -- //
                $("#recapitulatif_souscripteur_region").html(resultat.notification.donnee);
            } else {
                // -- Notifier -- //
                dsMessage_Box('danger', resultat.notification.message + '!');
            }
        },
        error: function () {
            // -- Notifier -- //
            dsMessage_Box('danger', 'Une erreur est survenue!');
        }
    });

}

// -- Modal de configuration du prelevement Cheque et Virement -- //
function dsModal_Cheque_Virement(donnee) {

    // -- Définition de l'action à la fermeture du modal -- //
    $('#modal_message').on('hidden.bs.modal',
        function () {
            // -- Redirection -- //
            dsHref('/Paiement/Terminer/?dt=' + donnee.dt);
        }
    );
    // -- Mise à jour du contenu du bouton fermer -- //
    $('#modal_message_btn_fermer').html('<i class="glyphicon glyphicon-check"></i> OK');
    // -- Mise à jour de la class css du bouton OK -- //
    $('#modal_message_btn_fermer').removeClass('btn btn-sm btn-light float-left');
    $('#modal_message_btn_fermer').addClass('btn btn-lg btn-light float-left');
    // -- Mise à jour de la taille -- //
    $('#modal_message_taille').removeClass('modal-dialog modal-sm');
    $('#modal_message_taille').addClass('modal-dialog');
    // -- Définir l'entête -- //
    $('#modal_message_titre').html('<i class="fa ' + ((donnee.mode_paiement === 3) ? 'fa-wpforms'
                                                                                   : 'fa-exchange') + '"></i> ' + donnee.titre);
    // -- Definir le message -- //
    $('#modal_message_text').html(donnee.message);
    // -- Afficher -- //
    $('#modal_message').modal('show');

}

// -- Modal de configuration du prelevement SEPA -- //
function dsModal_SEPA(donnee) {

    // -- Changer la visibilité de l'entreprise ou des personnes physique dans le formulaire -- //
    $(".ds-temp-modal_prelevement_sepa").on("click",
            function () {

                // -- Variables -- //
                var div_personne_physique   = $('#modal_prelevement_sepa_form_personne_physique');
                var div_entreprise          = $('#modal_prelevement_sepa_form_entreprise');
                var prenom                  = $('#modal_prelevement_sepa_form_prenom');
                var nom                     = $('#modal_prelevement_sepa_form_nom');
                var raison_sociale          = $('#modal_prelevement_sepa_form_raison_sociale');

                // -- Mise à jour de la visibilité -- //
                if (div_entreprise.css('display') === 'none') {
                    // -- Mise à jour visibilité -- //
                    div_personne_physique.hide();
                    div_entreprise.slideToggle("slow");
                    // -- Reset valeur -- //
                    prenom.val(null);
                    nom.val(null);
                } else {
                    // -- Mise à jour visibilité -- //
                    div_entreprise.hide();
                    div_personne_physique.slideToggle("slow");
                    // -- Reset valeur -- //
                    raison_sociale.val(null);
                }

            }
        );

    // -- Changer la visibilité de l'entreprise ou des personnes physique dans le formulaire -- //
    $("#modal_prelevement_sepa_submit").on("click",
            function () {

                // -- Variables -- //
                var form = $('#modal_prelevement_sepa_form');

                // -- Tester si le formulaire est valide -- //
                if (form.parsley().validate()) {

                    // -- Afficher/Cacher l'etat de chargement de la page -- //
                    dsAfficher_Page_Chargement(true, null);

                    // -- Ajax -- //
                    $.ajax({
                        type: 'POST',
                        url: '/Souscription/Paiement',
                        data: form.serialize() + '&mode_paiement=8' +
                                                 '&action_prelevement_sepa=true' +
                                                 '&confirmation=false',
                        success: function (resultat) {
                            // -- Traiter en cas de réussite -- //
                            if (!resultat.notification.est_echec) {
                                // -- Mise à jour des information dans le panel -- //
                                $('#modal_prelevement_sepa_recapitulatif_creancier').html(resultat.notification.donnee.creancier);
                                $('#modal_prelevement_sepa_recapitulatif_nom').html(resultat.notification.donnee.nom);
                                $('#modal_prelevement_sepa_recapitulatif_adresse').html(resultat.notification.donnee.adresse);
                                $('#modal_prelevement_sepa_recapitulatif_type').html(resultat.notification.donnee.type);
                                $('#modal_prelevement_sepa_recapitulatif_reference').html(resultat.notification.donnee.reference);
                                $('#modal_prelevement_sepa_recapitulatif_date').html(resultat.notification.donnee.date);
                                $('#modal_prelevement_sepa_recapitulatif_iban').html(resultat.notification.donnee.iban);
                                $('#modal_prelevement_sepa_recapitulatif_bic').html(resultat.notification.donnee.bic);
                                $('#modal_prelevement_sepa_recapitulatif_nom_sepa').html(
                                    (resultat.notification.donnee.raison_sociale) ? resultat.notification.donnee.raison_sociale
                                                                                  : resultat.notification.donnee.civilite + ' ' + resultat.notification.donnee.nom_sepa + ' ' + resultat.notification.donnee.prenom_sepa
                                );
                                $('#modal_prelevement_sepa_recapitulatif_souscripteur').html(resultat.notification.donnee.souscripteur);
                                // -- Changer l'etape à afficher dans le panel -- //
                                dsPanelEtapePrelevementSEPA(1, 2);
                            }
                            else {
                                // -- Alert -- //
                                dsAlert('danger', resultat.notification.message, null);
                            }
                            // -- Afficher/Cacher l'etat de chargement de la page -- //
                            dsAfficher_Page_Chargement(false, null);
                        },
                        error: function (xhr, status, error) {
                            // -- Notifier -- //
                            dsAlert('danger', 'Une erreur est survenue: ' + JSON.parse(xhr.responseText).Message, null);
                            // -- Afficher/Cacher l'etat de chargement de la page -- //
                            dsAfficher_Page_Chargement(false, null);
                        }
                    });

                }

            }
    );

    // -- Action de paiement du prélèvement SEPA -- //
    $("#modal_prelevement_sepa_paiement").on("click",
            function () {

                // -- Afficher/Cacher l'etat de chargement de la page -- //
                dsAfficher_Page_Chargement(true, null);

                // -- Ajax -- //
                $.ajax({
                    type: 'POST',
                    url: '/Souscription/Paiement',
                    data: {
                        mode_paiement: 8,
                        action_prelevement_sepa: false,
                        confirmation: true
                    },
                    success: function (resultat) {
                        // -- Traiter en cas de réussite -- //
                        if (!resultat.notification.est_echec) {
                            // -- Cacher le modal SEPA -- //
                            $('#modal_prelevement_sepa').modal('hide');
                            // -- Redirection chez Payline -- //
                            dsHref(resultat.notification.donnee.url_redirection);
                            // -- Notification de redirection -- //
                            dsMessage_Box('success', resultat.notification.donnee.message);
                        }
                        else {
                            // -- Alert -- //
                            dsAlert('danger', resultat.notification.message, 'dsAlert2');
                        }
                        // -- Afficher/Cacher l'etat de chargement de la page -- //
                        dsAfficher_Page_Chargement(false, null);
                    },
                    error: function (xhr, status, error) {
                        // -- Notifier -- //
                        dsAlert('danger', 'Une erreur est survenue: ' + JSON.parse(xhr.responseText).Message, 'dsAlert2');
                        // -- Afficher/Cacher l'etat de chargement de la page -- //
                        dsAfficher_Page_Chargement(false, null);
                    }
                });

            }
    );

    // -- Action de téléchargement du ducoment prélèvement SEPA -- //
    $("#modal_prelevement_sepa_telecharger_document").on("click",
            function () {

                // -- Afficher/Cacher l'etat de chargement de la page -- //
                dsAfficher_Page_Chargement(true, null);

                // -- Ajax -- //
                $.ajax({
                    type: 'POST',
                    url: '/DS/Telecharger_Document',
                    data: {
                        reference: donnee.reference_document
                    },
                    success: function (resultat) {
                        // -- Traiter en cas de réussite -- //
                        if (!resultat.notification.est_echec) {
                            // -- Telecharger le fichier -- //
                            dsTelecharger_Fichier(
                                resultat.notification.donnee.fichier,
                                resultat.notification.donnee.libelle
                            );
                            // -- Alert -- //
                            dsAlert('success', resultat.notification.donnee.message, 'dsAlert2');
                        }
                        else
                        {
                            // -- Alert -- //
                            dsAlert('danger', resultat.notification.message, 'dsAlert2');
                        }
                        // -- Afficher/Cacher l'etat de chargement de la page -- //
                        dsAfficher_Page_Chargement(false, null);
                    },
                    error: function (xhr, status, error) {
                        // -- Notifier -- //
                        dsAlert('danger', 'Une erreur est survenue: ' + JSON.parse(xhr.responseText).Message, 'dsAlert2');
                        // -- Afficher/Cacher l'etat de chargement de la page -- //
                        dsAfficher_Page_Chargement(false, null);
                    }
                });

            }
    );

    // -- Désactiver le clic en dehors du modal sortie -- //
    $('#modal_prelevement_sepa').modal({
        backdrop: 'static',
        keyboard: false
    });
    // -- Reset le formulaire -- //
    $('#modal_prelevement_sepa_form')[0].reset();
    // -- Mise à jour des informations du souscripteur dans le formulaire -- //
    $('#modal_prelevement_sepa_form_nom').val(donnee.nom);
    $('#modal_prelevement_sepa_form_prenom').val(donnee.prenom);
    $('#modal_prelevement_sepa_form_email').val(donnee.email);
    $('#modal_prelevement_sepa_form_pays').val(donnee.code_pays);
    // -- Afficher -- //
    $('#modal_prelevement_sepa').modal('show');

}

// -- Afficher une alerte sur un element -- //
function dsAlert(type, message, id_element) {

    // -- Mise à jour de id_element -- //
    id_element = (id_element == null || id_element == undefined) ? 'dsAlert'
                                                                 : id_element;

    // -- Afficher l'alert -- //
    $('#' + id_element).html(
        '<div class="alert alert-' + type + '" alert-dismissible fade show role="alert">' +
            '<b>Information</b><br/>' +
            message +
            '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                '<span aria-hidden="true">&times;</span>' +
            '</button>' +
        '</div>'
    );

    // -- Supprimer l'alert après un temps défini -- //
    setTimeout(
        function () {
            // -- Fermer l'alert -- //
            $('#' + id_element + ' .alert').alert('close');
        },
        10000
    );

}

// -- Lorsque le document est chargé -- //
$(
    function () {

        // -- Affichier le progress bar -- //
        try {

            NProgress.start();

        } catch (e) { }

        // Create overlay and append to body:
        try {
            
        $('<div id="frame_chargement"/>').css({
            position: 'fixed',
            top: 0,
            left: 0,
            width: '100%',
            height: $(window).height() + 'px',
            opacity: 0.4,
            zIndex: 999999,
            cursor: 'wait',
            //background: 'lightgray url(../../Resources/images/gif/Logo_ds50x50.gif) no-repeat center'
        }).hide().appendTo('body');

        // -- Cacher la page en chargement -- //
        $('<div id="frame_cacher"/>').css({
            position: 'fixed',
            top: 0,
            left: 0,
            width: '100%',
            height: $(window).height() + 'px',
            opacity: 1,
            zIndex: 999999,
            cursor: 'wait',
            //background: 'white url(../../Resources/images/gif/Logo_ds50x50.gif) no-repeat center'
        }).hide().appendTo('body');
        
        } catch (e) { }

        // -- Notifier l'utilisation des cookiees -- //
        // -- Lors du click sr le bouton de validation -- //
        $("#bt_valide_cookie_use").on("click",
            function () {
                dsSetCookie('cookiewarning', 1, 365);
                $("#cookie_panel").slideToggle("slow");
            }
        );

        // -- Notifier l'utilisation des cookies -- //
        if (dsGetCookie('cookiewarning') == "") {
            $("#cookie_panel").slideToggle("slow");
        }
        // -- ./Notifier l'utilisation des cookiees -- //

        // -- Charger le masque sur les champ date -- //
        try {

            $("[data-mask]").inputmask("dd/mm/yyyy", { "placeholder": "dd/mm/yyyy" });

        } catch (e) { }

        // -- Click pour passer à l'étape suivant -- //
        $("#btn_suivant").on("click",
            function () {

                // -- Variables -- //
                var form_1 = $('#form_etape_1');
                var form_2 = $('#form_etape_2');
                var bouton_etape = $('.nav.nav-tabs .nav-item .nav-link.active').attr('href');

                // -- Afficher/Cacher l'etat de chargement de la page -- //
                dsAfficher_Page_Chargement(true, null);

                // -- Etape 1 -- //
                if (bouton_etape === '#tab-page-1') {
                    // -- Tester si le formulaire est valide -- //
                    if (form_1.parsley().validate()) {
                        // -- Vérifie que le calcul a été effectué -- //
                        if (parseInt($('#calcul_tarif_effectue').val()) === 1) {
                            // -- Mettre à jour le panel etape -- //
                            dsPanelEtape(1, 2);
                        } else {
                            // -- Notifier -- //
                            dsMessage_Box('info', 'Vous devez procéder au calcul de votre tarif avant de passer à l\'étape suivante.');
                        }
                    }
                    // -- Afficher/Cacher l'etat de chargement de la page -- //
                    dsAfficher_Page_Chargement(false, null);
                }
                // -- Etape 2 -- //
                else if (bouton_etape === '#tab-page-2') {
                    // -- Tester si le formulaire est valide -- //
                    if (form_2.parsley().validate()) {
                        // -- Mise à jour du numéro de téléphone -- //
                        $('#recapitulatif_souscripteur_mobile').val(
                            $('#recapitulatif_souscripteur_mobile').intlTelInput("getNumber")
                        );

                        // -- Variables -- //
                        var civilite_assure         = [];
                        var nom_assure              = [];
                        var prenom_assure           = [];
                        var nombre_beneficiaire     = $('input:radio[name=nombre_beneficiaire]:checked').val();

                        // -- Réccupération des date de naissance des bénéfciaires -- //
                        for (var i = 1; i <= nombre_beneficiaire; i++) {
                            // -- Ajout dans la liste -- //
                            civilite_assure.push($('#hide_form_assuree_civilite_' + i).val());
                            nom_assure.push($('#hide_form_assuree_nom_' +i).val());
                            prenom_assure.push($('#hide_form_assuree_prenom_' + i).val());
                        }

                        // -- Ajax -- //
                        $.ajax({
                            type: 'POST',
                            url: '/Souscription/Information_Devis',
                            data: form_2.serialize() + '&civilite_assure='  + JSON.stringify(civilite_assure) + 
                                                       '&nom_assure='       + JSON.stringify(nom_assure) + 
                                                       '&prenom_assure='    + JSON.stringify(prenom_assure),
                            success: function (resultat) {
                                // -- Traiter en cas de réussite -- //
                                if (!resultat.notification.est_echec) {
                                    // -- Recharger les liste des assurés -- //
                                    dsRechargerListeAssure(0, 3);
                                    // -- Mettre à jour le panel etape -- //
                                    dsPanelEtape(2, 3);
                                    // -- Notifier -- //
                                    dsNotification(1, null, resultat.notification.donnee);
                                }
                                else {
                                    // -- Notifier -- //
                                    dsMessage_Box('danger', resultat.notification.message);
                                }
                                // -- Afficher/Cacher l'etat de chargement de la page -- //
                                dsAfficher_Page_Chargement(false, null);
                            },
                            error: function () {
                                // -- Notifier -- //
                                dsMessage_Box('danger', 'Une erreur est survenue');
                                // -- Afficher/Cacher l'etat de chargement de la page -- //
                                dsAfficher_Page_Chargement(false, null);
                            }
                        });
                    } else {
                        // -- Afficher/Cacher l'etat de chargement de la page -- //
                        dsAfficher_Page_Chargement(false, null);
                    }
                }
                // -- Etape 3 -- //
                else {
                    // -- Afficher/Cacher l'etat de chargement de la page -- //
                    dsAfficher_Page_Chargement(false, null);
                }

            }
        );

        // -- Click pour passer à l'étape suivant -- //
        $("#btn_calculer_paiement").on("click",
            function () {

                // -- Variables -- //
                var mode_paiement = $('input:radio[name=mode_paiement]:checked').val();

                // -- Afficher/Cacher l'etat de chargement de la page -- //
                dsAfficher_Page_Chargement(true, null);

                // -- Ajax -- //
                $.ajax({
                    type: 'POST',
                    url: '/Souscription/Paiement',
                    data: {
                        mode_paiement           : mode_paiement,
                        action_prelevement_sepa : false,
                        confirmation            : false,
                    },
                    success: function (resultat) {
                        // -- Traiter en cas de réussite -- //
                        if (!resultat.notification.est_echec) {
                            // -- Prelevement CB -- //
                            if (resultat.notification.donnee.mode_paiement === 1) {
                                // -- Redirection chez Payline -- //
                                dsHref(resultat.notification.donnee.url_redirection);
                                // -- Notification de redirection -- //
                                dsMessage_Box('success', resultat.notification.donnee.message);
                            }
                            // -- Prelevement Cheque -- //
                            else if (resultat.notification.donnee.mode_paiement === 3 || resultat.notification.donnee.mode_paiement === 5) {
                                // -- Modal de configuration du prelevement Cheque ou Virement -- //
                                dsModal_Cheque_Virement(resultat.notification.donnee);
                            }
                            // -- Prelevement SEPA -- //
                            else if (resultat.notification.donnee.mode_paiement === 8) {
                                // -- Modal de configuration du prelevement SEPA -- //
                                dsModal_SEPA(resultat.notification.donnee);
                            } else {
                                // -- Notifier -- //
                                dsMessage_Box_MD('info', resultat.notification.donnee.message);
                            }
                        }
                        else {
                            // -- Notifier -- //
                            dsMessage_Box('danger', resultat.notification.message);
                        }
                        // -- Afficher/Cacher l'etat de chargement de la page -- //
                        dsAfficher_Page_Chargement(false, null);
                    },
                    error: function () {
                        // -- Notifier -- //
                        dsMessage_Box('danger', 'Une erreur est survenue');
                        // -- Afficher/Cacher l'etat de chargement de la page -- //
                        dsAfficher_Page_Chargement(false, null);
                    }
                });


            }
        );

        // -- Click pour passer à l'étape précédente -- //
        $("#btn_precedent").on("click",
            function () {

                // -- Variables -- //
                var bouton_etape = $('.nav.nav-tabs .nav-item .nav-link.active').attr('href');

                // -- Etape 3 -- //
                if (bouton_etape === '#tab-page-3') {
                    // -- Mettre à jour le panel etape -- //
                    dsPanelEtape(3, 2);
                }
                // -- Etape 2 -- //
                else if (bouton_etape === '#tab-page-2') {
                    // -- Mettre à jour le panel etape -- //
                    dsPanelEtape(2, 1);
                }

            }
        );

        // -- Ajouter un nouveau validateur des format de téléphone -- //
        try {

            Parsley.addValidator('intltelinput', {
                requirementType: 'string',
                validateString: function (value, id_element) {
                    var valide = $(id_element).intlTelInput("isValidNumber");
                    return valide;
                },
                priority: 22,
                messages: {
                    fr: "Ce numéro de téléphone incorrect.",
                }
            });

        } catch (e) { }

        // -- Définir le pluging icheck sur les composants -- //
        try {

            $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
                checkboxClass: 'icheckbox_flat-green',
                radioClass: 'iradio_flat-green'
            });

        } catch (e) { }

        // -- Finaliser le chargement du progress bar -- //
        try {

            NProgress.done();

        } catch (e) { }
    }
);