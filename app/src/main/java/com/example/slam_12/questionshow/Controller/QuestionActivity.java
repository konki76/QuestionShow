package com.example.slam_12.questionshow.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slam_12.questionshow.Model.Question;
import com.example.slam_12.questionshow.Model.QuestionBank;
import com.example.slam_12.questionshow.R;

import java.util.Arrays;
import java.util.List;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {
    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;
    private Button mQuestion;
    private Button mAnswer;
    private int mNumberOfQuestions;
    private boolean mRectoVerso = true;

    public static final String BUNDLE_STATE_QUESTION = "currentQuestion";
    private boolean mEnableTouchEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        mQuestionBank = this.generateQuestions();

        if (savedInstanceState != null) {
            mNumberOfQuestions = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        } else {
            mNumberOfQuestions = 9;
        }
        mQuestion = (Button) findViewById(R.id.question_btn);
        mAnswer = (Button) findViewById(R.id.answer_btn);
        Button b_retour =(Button)findViewById(R.id.retour_btn);

        final Intent intent = getIntent();
        String matiere = intent.getStringExtra(MainActivity.MAT_NAME);
        final TextView tv1 = (TextView)findViewById( R.id.question_txt );
        tv1.setText( matiere );

        b_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent();
                i2.putExtra(MainActivity.MAT_NAME, "valeur de retour");
                QuestionActivity.this.setResult(1, i2);
                QuestionActivity.this.finish();
            }
        });

        mQuestion.setOnClickListener(this);
        mAnswer.setOnClickListener(this);
        mCurrentQuestion = mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);
    }
    @Override
    public void onClick(View v) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mEnableTouchEvents = true;

                // If this is the last question, ends the game.
                // Else, display the next question.
                if (mRectoVerso == false) {
                    if (--mNumberOfQuestions == 0) {
                        // End the game
                        endGame();
                    } else {
                        mCurrentQuestion = mQuestionBank.getQuestion();
                        displayQuestion(mCurrentQuestion);
                        mRectoVerso = true;
                        mAnswer.setText("");
                    }
                } else {
                    mRectoVerso = false;
                    mAnswer.setText(mCurrentQuestion.getAnswer());
                }
            }
        }, 0); // LENGTH_SHORT is usually 2 second long
    }
    private void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Well done!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // End the activity
                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }

    private void displayQuestion(final Question question) {
        mQuestion.setText(mCurrentQuestion.getQuestion());
    }
    private QuestionBank generateQuestions() {
        Question E1 = new Question("* Définir l’entreprise. ", "Ensemble structuré d'éléments matériels, humains et financiers organisés en vue de produire des biens et des services destinés à être vendus. ");
        Question E2 = new Question("** Distinguer les petites, les moyennes et les grandes entreprises. ", "- Petite entreprise = entre 10 et 49 salariés, - Moyenne entreprise = entre 50 et 249 salariés - Grande entreprise = entre 250 et 1999 salariés. ");
        Question E3 = new Question("** Expliquer la répartition des coûts de production des constructeurs informatiques. ", "Répartition traditionnelle des coûts de production : les coûts variables augmentent à mesure de l’augmentation des quantités produites tandis que les coûts fixes évoluent par palier. ");
        Question E4 = new Question("* Je suis un lieu sur lequel se rencontrent les agents économiques pour échanger. Qui suis-je ? ", "Le marché. ");
        Question E5 = new Question("*** Donner deux raisons pour lesquelles les agents économiques échangent entre eux. ", "1/ Raison économique : acquérir des biens que l’on ne peut fabriquer soi-même 2/ Raison sociale : éviter l’affrontement pour obtenir des biens. ");
        Question E6 = new Question("** Définir l’homogénéité. ", "C‘est l’une des conditions d’un marché concurrentiel : le demandeur s’intéresse davantage aux caractéristiques du produit qu’à la marque. Les produits sont perçus de manière similaire pour l’acheteur qui cherche avant tout à satisfaire un besoin. ");
        Question E7 = new Question("* Je suis un agent économique qui acquière un bien, un service, du capital ou du travail sur le marché. Qui suis-je ? ", "Je suis un demandeur. ");
        Question E8 = new Question("* Donner une définition de la monnaie fiduciaire. ", "Fiducia = confiance Il s’agit des pièces et des billets. ");
        Question E9 = new Question("** Présenter les quatre conditions d’un marché concurrentiel. ", "Un marché est concurrentiel si quatre conditions sont réunies : l’atomicité de l’offre, l’homogénéité des produits, la transparence des prix et la fluidité d’accès au marché. + Explications. ");
        Question E10 = new Question("* Déterminer l’évolution de l’offre si le prix augmente. ", "L’offre augmente. ");
        Question E11 = new Question("* Présenter les caractéristiques d’un monopole. ", "Marché avec un seul offreur pour une multitude de demandeurs. Sur ce type de marché, seul l’offreur fixe le prix (donc à un niveau potentiellement supérieur au prix d’équilibre). ");
        Question E12 = new Question("** Définir la notion de décision telle qu’elle a été proposée par Mintzberg. ", "Une décision, c’est mettre en œuvre un processus, qui aboutira, in fine, à un choix permettant de réduire l’incertitude et donc d’orienter l’avenir. ");
        Question E13 = new Question("** Lister les différentes étapes du processus de prise de décision. ", "Déterminer le besoin / le problème, rechercher des solutions à ce problème en fonction des contraintes (budget /prix, +, -), choisir une solution à tester, observer l’effet obtenu et comparer avec l’effet attendu, choisir les éventuelles actions correctives pour atteindre l’objectif fixé. ");
        Question E14 = new Question("** Citer les quatre qualités que doit revêtir une information pour être utile à la prise de décision. ", "Elle doit être pertinente, fiable, actualisée et disponible. ");
        Question E15 = new Question("** Proposer deux solutions pour réduire l’asymétrie d’information sur le marché des biens et services. ", "L’affichage des prix et des caractéristiques des produits, obligation d’information du client, internet, les notices d’utilisation, les contrôles obligatoires de certains produits (chaudières, voitures, …) ");
        Question E16 = new Question("* Citer un terme anglais permettant de désigner l’externalisation du système d’information. ", "Outsourcing  ");
        Question E17 = new Question("*** Distinguer IaaS, SaaS et PaaS. Iaas : ", "Infrastructure as a service, il s’agit d’externaliser le matériel, l'hébergement et le Framework. SaaS : Software as a Service, Il s’agit d’externaliser aussi les logiciels PaaS : Platform as a service. Il s’agit de se connecter à distance à un portail de services ");
        Question E18 = new Question("** Nommer la théorie qui permet d’identifier les besoins des clients. ", "La théorie de la valeur. Objectif : adapter un produit aux besoins des clients. ");
        Question E19 = new Question("* Vrai ou Faux : L’État intervient sur le fonctionnement des marchés. ", "VRAI ");
        Question E20 = new Question("* Vrai ou Faux : Une externalité est un effet voulu par l’agent économique qui le produit. ", "Faux,  L’activité économique est voulue par l’agent économique mais pas ses conséquences. ");
        Question E21 = new Question("** Définir la notion de bien public. ", "C’est un bien dont l’utilisation est non-rivale (la C du bien par un agent n'empêche pas sa C par un autre) et non-exclusive, (il n’est pas possible d’empêcher un agent de consommer ce bien). ");
        Question E22 = new Question("* Préciser quelle est la place de l’État dans l’économie au 19ème siècle. ", "État gendarme Peu d’intervention dans l’économie. ");
        Question E23 = new Question("** Proposer deux solutions que l’État peut mettre en œuvre pour gérer les externalités négatives. ", "- En réglementant les activités polluantes, - En taxant (principe du pollueur payeur), - En mettant en place un marché des droits à polluer. - … ");
        Question E24 = new Question("* Préciser la répartition les échanges internationaux aujourd’hui. (proportion de marchandises, services, produits primaires). Aujourd’hui, répartition assez stable : ", "- 65% de marchandises, - 22% de services, - 13% de produits primaires. ");
        Question E25 = new Question("* Vrai ou Faux : Depuis la fin de la seconde guerre mondiale, le volume des échanges commerciaux est resté stable. ", "Faux, Il a fortement augmenté. ");
        Question E26 = new Question("* Vrai ou Faux : La théorie des avantages relatifs se justifie par la participation de chaque pays au commerce international. ", "VRAI ");
        Question E27 = new Question("** Citer trois effets de l’internationalisation sur un secteur d’activité. ", "L’internationalisation permet les économies d’échelle, la conquête de nouveaux marchés, le contournement de certains obstacles protectionnistes à l’entrée ou à la sortie des marchés, des gains grâce aux taux de change, l’optimisation fiscale,… ");
        Question E28 = new Question("** Expliquer ce que signifie l’acronyme TIC. ", "TIC = technologie de l’information et de la communication. Ensemble des techniques utilisées dans le traitement et la transmission des informations (informatique, Internet et télécommunications). ");
        Question E29 = new Question("* Vrai ou Faux : Il peut y avoir développement sans croissance. ", "FAUX ");
        Question E30 = new Question("* Déterminer si le PIB par habitant est un indicateur de croissance ou de développement. ", "Il est un indicateur de développement car il mesure la répartition de la richesse créé sur un territoire. ");
        Question E31 = new Question("** Définir la notion de développement durable. ", "Ce développement sera dit durable s’il « répond aux besoins du présent sans compromettre la capacité des générations futures à répondre à leurs propres besoins ». ");
        Question E32 = new Question("*** Expliquer comment Google réalise son chiffre d’affaires. ", "Google utilise un modèle biface de fixation des prix : les services en ligne sont gratuits pour les internautes mais payants pour les annonceurs. Le chiffre d’affaires de Google provient donc des annonceurs publicitaires. ");
        Question E33 = new Question("** En 2011, Bull s’est vu décerner le « 1er prix européen » pour la mise en œuvre des meilleures pratiques énergétiques au sein d’un centre informatique. Qualifier cette pratique. ", "Bull a mis en place une politique de Green-IT au sein de l’entreprise. ");
        Question E34 = new Question("**Distinguer branche d’activité et secteur d’activité.", "Branche d’activité = regroupement d’entreprises fabriquant le même type de produit. Secteur d’activité = regroupement d’entreprises ayant la même activité principale.");
        Question E35 = new Question("* Nommer une entreprise du secteur informatique ayant plus de 2000 salariés.", "C’est un géant du numérique. + Ex : Cisco (74 000 emplois en 2013), Wipro (158200 en 2015)… mais peu d’entreprises françaises (Orange 158000 en 2015).");
        Question E36 = new Question("** Expliquer la répartition des coûts de production dans une entreprise de services informatiques.", "Ces entreprises ont très peu de coûts variables mais des coûts fixes importants (R&D, énergie,…).");
        Question E37 = new Question("* Citer trois catégories de marché.", "Le marché de biens et services, Le marché du travail, Le marché des capitaux.");
        Question E38 = new Question("* Proposer 2 idées différentes expliquant le rôle du prix pour le producteur.", "Le prix permet au producteur de s’enrichir. Le prix est aussi un indicateur de rentabilité de l’activité productive.");
        Question E39 = new Question("** Définir la transparence.", "C’est l’une des conditions d’un marché concurrentiel : le demandeur dispose d’une information claire, précise et suffisante pour pouvoir effectuer un choix de consommation efficace.");
        Question E40 = new Question("** Expliquer ce qu’est une asymétrie d’information.", "C’est une situation par laquelle un agent économique dispose d’une information insuffisante ou imparfaite pour effectuer un choix. Il se trouve donc dans une situation d’infériorité par rapport à son co-échangeur.");
        Question E41 = new Question("* Donner une définition de la monnaie scripturale.", "La monnaie scripturale est une monnaie dématérialisée qui n’existe que par mouvements entre comptes bancaires.");
        Question E42 = new Question("** Indiquer la conséquence d’un excès de demande pour l’offre.", "C’est une aubaine pour l’offreur car les produits étant rares, les prix vont augmenter. En revanche, c’est une situation néfaste pour le consommateur pour pourra acheter moins de produits avec le même budget à disposition.");
        Question E43 = new Question("*** Préciser sur quel type de marché évolue une entreprise de conception de site web lorsqu’elle achète du matériel de bureau.", "Sur un marché en BtoC (business to consumer) car l’entreprise achète un logiciel en dehors de sa sphère de compétence. Elle sera donc considérée comme un consommateur. Raisonnement attendu.");
        Question E44 = new Question("* Vrai ou Faux : Un oligopole est un marché comportant une multitude d’offreurs pour quelques demandeurs. ", "Faux : c’est l’inverse.");
        Question E45 = new Question("* Vrai ou Faux : Au prix d’équilibre la situation des agents économiques est optimale. ", "VRAI");
        Question E46 = new Question("* Préciser qui a modélisé le processus de prise de décision et sous quelle forme.", "Herbert Simon a modélisé le processus de prise de décision sous la forme du modèle IMC.");
        Question E47 = new Question("** Expliquer l’affirmation suivante : « un agent économique est un homo œconomicus ». ", "Cela signifie qu’un agent économique prend des décisions rationnelles, c’est-à-dire toujours celles qui servent le mieux ses intérêts, donc des décisions logiques et calculées.");
        Question E48 = new Question("** Donner une définition du système d’information.", "C’est un ensemble organisé de ressources (matériels, logiciels, personnel, données et procédures) qui permet de collecter, de classifier, de traiter et de diffuser de l'information.");
        Question E49 = new Question("** Distinguer cloud privé et cloud public.", "Cloud privé : Solution d'hébergement interne à l’organisation. Cloud public : les serveurs d’un hébergeur externe accueillent les ressources de différents clients.");
        Question E50 = new Question("* Préciser le nom du théoricien qui a développé la théorie de la chaîne de valeur. ", "La chaîne de valeur est une théorie développée par Mickaël Porter en 1986.");
        Question E51 = new Question("*** Présenter les cinq forces qui influencent la situation concurrentielle sur un marché.", "Les concurrents directs, Les entrants potentiels, Le pouvoir de négociation des clients, Le pouvoir de négociation des fournisseurs, Les produits de substitution, (Les contraintes légales).");
        Question E52 = new Question("* Vrai ou Faux : Les fonctions régaliennes peuvent être déléguées par l’État à des agents économiques compétents. ", "FAUX");
        Question E53 = new Question("* Vrai ou Faux : Un bien est rendu public pour favoriser le comportement du passager clandestin. ", "Faux, c’est pou l’éviter.");
        Question E54 = new Question("** Définir la notion d’externalité.", " Une externalité est un effet externe positif ou négatif provoqué par les actions de production ou de consommation d’un agent économique sur le bien-être des autres agents économiques.");
        Question E55 = new Question("* Préciser quelle est la place de l’État dans l’économie au 20ème siècle.", "État providence L’Etat intervient dans l’activité économique.");
        Question E56 = new Question("* Expliquer pourquoi l’État prend en charge les biens publics.", "#NOM?");
        Question E57 = new Question("* Vrai ou Faux : Un bruit est une externalité négative. ", "VRAI");
        Question E58 = new Question("* Vrai ou Faux : Les pays ne participent pas de manière égale aux échanges internationaux. ", "Vrai, 22 pays assurent 75% des échanges");
        Question E59 = new Question("* Identifier les principaux pays participant au commerce mondial.", " Les pays de la Triade (52% en 2013), mais les les BRICS (~30%) prennent de plus en plus de place dans les échanges.");
        Question E60 = new Question("* Définir le terme « Norme ».", "Une norme est un document de référence élaboré par un organe de normalisation reconnu comme l’ISO et l’AFNOR en France.");
        Question E61 = new Question("*** Citer deux effets positifs et deux effets négatifs des technologies numériques sur l’activité économique.", " + : Permet ä de la productivité des salariés (pas de tâches répétitives, meilleur accès à l’information,…), favorise les organisations structurelles plus souples / - : Problème d’ergostressie, floue entre vie privée et vie professionnelle, coûts (matériel, formation,…)");
        Question E62 = new Question("* Vrai ou Faux : Le développement durable ne prend en compte que des variables sociales et environnementales. ", "Faux, ce concept prend également en compte des variables économiques, culturelles et de gouvernance.");
        Question E63 = new Question("** Le calcul du PIB présente des inconvénients. Citer en au moins deux.", " - Certaines activités ne sont pas comptabilisées alors qu’elles sont productives : bénévolat, bricolage, travail au noir - Certaines activités polluantes sont comptabilisées - Le PIB sous-estime les activités non-marchandes.");
        Question E64 = new Question("** Expliquer ce qu’est la démarche de Green-IT.", "C’est une démarche éco-responsable mise en œuvre dans les entreprises du secteur informatique.");
        Question E65 = new Question("** Expliquer ce qu’est ce que le Yield Management.", "C’est un modèle de tarification qui évolue en fonction de critères préétablis : période, niveau des ventes pour un meilleur arbitrage entre l’offre et la demande.");
        Question E66 = new Question("*** Renault a mis en place un EDI pour mieux communiquer avec ses sous-traitants. Présenter les avantages organisationnels et financiers de cette pratique.", "+ organisationnels : diminution des erreurs donc des litiges, meilleure planification des stocks, réduction du temps de logistique / + financiers : réduction des coûts (moins de papier), réduction du cycle de transaction.");
        Question E67 = new Question("* Présenter l’ESN.", "ESN = Entreprise de Services Numériques Entreprises qui proposent des services numériques à d’autres entreprises : cloud, formation, conseils, maintenance,… Ex : Alteca");
        Question E68 = new Question("* Définir la notion de production.", "La production correspond à la transformation de ressources en produit finis ou semi-fini : les biens et ou les services.");
        Question E69 = new Question("* Préciser ce qu’est ce qu’un avantage concurrentiel.", "C’est un avantage qu’une entreprise détient ou peut détenir à l’avenir par rapport à ses concurrents et qui lui permet d’atteindre une position dominante sur le marché et de la conserver.");
        Question E70 = new Question("** Lister les 6 catégories d’agents économiques et présenter leur rôle respectif.", "Entreprise : produire et investir ; Banque : crédit et gestion d’épargne  Administration : service non marchand  Association : service privé Ménage+ EI : consommer et produire Reste du monde : import/export.");
        Question E71 = new Question("* Proposer 2 idées différentes expliquant le rôle du prix pour le consommateur.", "Le prix est un élément de comparaison ; c’est aussi un indicateur de rareté, un moyen d’accéder à la satisfaction de ses besoins.");
        Question E72 = new Question("** Définir la fluidité.", "C’est l’une des conditions d’un marché concurrentiel : la libre entrée et sortie des offreurs sur le marché permet l’installation de nouveaux concurrents ou au contraire leur disparition de manière naturelle, sans barrière.");
        Question E73 = new Question("** Expliquer la loi de l’offre et de la demande.", "C’est la loi qui régit le fonctionnement des marchés concurrentiels et par laquelle les prix dépendent des quantités offertes et des quantités demandées. ↗Prix =↗ O et ↘D ↘Prix = ↘O et ↗D");
        Question E74 = new Question("** Indiquer la conséquence d’un excès d’offre pour la demande.", "C’est une aubaine pour le consommateur car les prix vont diminuer pour retrouver l’équilibre. En revanche, c’est une situation délicate pour les offreurs qui sont trop nombreux sur le marché.");
        Question E75 = new Question("** Citer quatre éléments qui influencent la consommation des ménages.", "Les décisions de consommation des ménages sont influencées par le prix des produits mais aussi par leur qualité, les effets de mimétismes, le goût, l’utilité, …");
        Question E76 = new Question("** Présenter les conditions pour qu’un marché soit concurrentiel.", "Il doit respecter les conditions suivantes : atomicité, homogénéité, transparence et fluidité. + Explication.");
        Question E77 = new Question("** Citer trois avantages et deux inconvénients du logiciel libre pour le concepteur de logiciel.", " + : gratuit ou quasi-gratuit / code source accessible donc logiciel adaptable / MAJ par une communauté d’utilisateurs. - : ne génère pas directement de chiffre d’affaires, risque d’abandon du logiciel par la communauté.");
        Question E78 = new Question("* Vrai ou Faux : Une situation optimale peut être une situation où un agent économique profite de tout sur le marché et les autres de rien.", "VRAI");
        Question E79 = new Question("* Présenter le modèle IMC dans la théorie de la décision.", " · I = Intelligence = Identification du problème   · M = Modélisation = Recherche de solutions       · C = Choix = Choix d’une solution.");
        Question E80 = new Question("** Expliquer pourquoi les économistes contemporains pensent-ils que les agents économiques prennent des décisions en situation de rationalité limitée.", "Parce que les agents économiques doivent faire face à des situations d’asymétrie d’information. Ils prennent donc des décisions satisfaisantes mais non optimales.");
        Question E81 = new Question("* Expliquer la notion d’infogérance.", "C’est le choix pour une entreprise d’externaliser tout ou partie de son système d’information.");
        Question E82 = new Question("** Citer trois avantages de l’informatique en nuage.", "La réduction des coûts de stockage de l’information en interne, pour profiter des compétences d’un spécialiste, la possibilité de se concentrer sur son cœur de métier, la disponibilité des données et des applications en tous lieux.");
        Question E83 = new Question("*** Expliquer la théorie de la chaîne de valeur.", "Elle consiste à diviser l’entreprise en un ensemble de services ou activités homogènes (activités principales et activités de soutien) qui concourent tous à la réalisation de la performance de l’entreprise.");
        Question E84 = new Question("** Je suis une personne de droit public constituée d’un ensemble d’institutions qui permettent l’administration et la protection du territoire. Qui suis-je ?", "État");
        Question E85 = new Question("* Vrai ou Faux : L’Autorité de la concurrence est une autorité administrative indépendante. ", "VRAI");
        Question E86 = new Question("** Présenter l’État.", " L’État est représenté par 4 catégories d’administration : administrations centrales, administration de sécurité sociale, administrations locales et autorités administratives indépendantes.");
        Question E87 = new Question("** Donner une définition du passager clandestin.", " Personne qui profite d’une situation pour ne pas en assumer les conséquences notamment financière.");
        Question E88 = new Question("** Préciser quelle est la place de l’État dans l’économie au 21ème siècle.", "L’État providence est critiqué. Il existe toujours mais se caractérise par moins d’intervention de l’État car cela a créé du déficit et n’a pas empêché le développement du chômage.");
        Question E89 = new Question("** Rappeler la méthode d’analyse d’un marché.", "Intro : définition du marché, présentation du marché concerné 1/ Analyse statique : le marché est-il concurrentiel ? 2/ Analyse dynamique : évolution du marché dans le temps. Conclusion");
        Question E90 = new Question("* Vrai ou Faux : Les ménages financent les biens publics. ", "VRAI Par le biais des prélèvements obligatoires.");
        Question E91 = new Question("** Expliquer la théorie HOS.", " Allocation optimale des ressources par l’échange : chaque pays a intérêt à se spécialiser dans les productions pour lesquels il dispose des facteurs de production les plus adaptés.");
        Question E92 = new Question("** Présenter la théorie des avantages comparatifs de David Ricardo.", " Chaque pays a intérêt à se spécialiser dans les productions pour lesquelles il a l’avantage le plus fort ou le désavantage le plus faible.");
        Question E93 = new Question("* Définir le terme de « Standard ».", "Un standard est un ensemble de règles élaborées par un petit nombre d’acteurs (des consortiums, des forums c'est-à-dire par des organisations non officielles) et communément adoptées sur un marché.");
        Question E94 = new Question("** Définir la notion de croissance.", " C’est la richesse créée par une Nation pendant une longue période.");
        Question E95 = new Question("*** Préciser le lien existant entre croissance et développement.", " Il n’y a pas de développement sans croissance. Mais la croissance ne s’accompagne pas nécessairement d’un développement du pays.");
        Question E96 = new Question("** Citer les trois principes sur lesquels se fondent le développement durable.", " - Principe de solidarité - Principe de précaution - Principe de prévention");
        Question E97 = new Question("** Nommer la démarche éco-responsable mise en œuvre dans les entreprises utilisatrices de produits informatiques, notamment pour les inciter à recycler.", "IT for Green.");
        Question E98 = new Question("** Micropole est une société de conseils, et d’ingénierie évoluant sur le marché de la Business Intelligence. Identifier sa branche et son secteur d’activité.", "Branche d’activité : ESN Secteur d’activité : tertiaire.");
        Question E99 = new Question("** Expliquer ce qu’est un EDI.", "L’échange de données informatisées est une technique qui permet les échanges, selon un format standardisé, entre ordinateurs connectés par liaisons spécialisées ou par un réseau (privatif) à valeur ajoutée (RVA)");
        Question E100 = new Question("* Préciser à quelle branche d’activité appartiennent les entreprises EBP et CEGID.", "Ce sont des éditeurs de logiciels.");
        Question E101 = new Question("* Expliquer ce qu’est le processus de production.", "C’est une succession d’opérations coordonnées et consommant des ressources pour aboutir au bien ou au service marchand.");
        Question E102 = new Question("** Expliquer les raisons pour lesquelles une entreprise recherche l’avantage concurrentiel.", "Cet avantage permet à l’entreprise de s’assurer une position dominante sur le marché et de bénéficier d’une rente (temporaire) de situation. Toute autre explication cohérente sera acceptée.");
        Question E103 = new Question("** Expliquer les règles de fixation des prix sur un marché concurrentiel. ", "Par le jeu de la loi de l’offre et de la demande. + Explication");
        Question E104 = new Question("** Définir l’atomicité.", "C’est l’une des conditions d’un marché concurrentiel : c’est la présence d’un grand nombreux d’offreurs et d’un grand nombre de demandeurs sur le marché pour qu’aucun d’entre eux à lui seul n’influencent la fixation du prix.");
        Question E105 = new Question("* Nommer l’agent économique qui met à disposition sur le marché un bien, un service, du travail ou du capital.", "C’est un offreur.");
        Question E106 = new Question("** Donner une définition de la monnaie.", "La monnaie est un actif liquide accepté par tous à sa valeur nominale pour effectuer des échanges ou pour épargner. Elle sert aussi d’unité de compte.");
        Question E107 = new Question("* Préciser l’évolution de la demande si le prix augmente.", "La demande diminue.");
        Question E108 = new Question("** Préciser de quoi dépend le prix de vente du producteur.", "Du coût de production, des prix de vente des concurrents, de l’importance de la demande.");
        Question E109 = new Question("** Présenter les quatre fonctions de la monnaie.", "La monnaie présente trois fonctions économiques… : instrument d’échange, réserve de valeur et unité de compte … et une fonction sociale : permettre de vivre en société, instrument de socialisation.");
        Question E110 = new Question("** Citer trois avantages et deux inconvénients du logiciel propriétaire pour le concepteur de logiciel.", " + : génère du chiffre d’affaires car code source caché, maintenance par l’entreprise créatrice du logiciel, logiciel sécurisé - : recrutement des concepteurs (coûts), pas de communauté pour les MAJ, évolutions soumises aux souhaits de l’entreprise créatrice.");
        Question E111 = new Question("* En situation d’excès de demande, il faut augmenter les prix pour revenir à l’équilibre sur le marché.", " VRAI");
        Question E112 = new Question("* Distinguer information et donnée.", "Une information est un renseignement utile pour la prise de décision et qui prend son sens dans un contexte. Une donnée est une information élémentaire pouvant être numérisée.");
        Question E113 = new Question("** Citer deux comportements irrationnels qui peuvent pousser un individu à consommer.", "Le mimétisme (volonté d’appartenir à un groupe), les comportements moutonniers (faire comme les autres sans se poser de questions), les goûts, les effets de mode,…");
        Question E114 = new Question("* Préciser la dénomination juridique de l’entreprise qui externalise son système d’information.", "L’entreprise donneuse d’ordre.");
        Question E115 = new Question("** Citer trois inconvénients de l’informatique en nuage.", "La sécurisation des données Les coûts liés à l’externalisation, La perte de compétences en interne, Le risque de dépendance au prestataire,…");
        Question E116 = new Question("*** Expliquer comment les technologies numériques peuvent permettre d’améliorer la chaîne de valeur.", "Meilleure réactivité des services par une information diffusée en temps réel, éviter les tâches répétitives, comprendre que chaque service participe à la création de valeur (relation client-fournisseur interne).");
        Question E117 = new Question("* Identifier la fonction de l’État permettant de couvrir les risques sociaux, de distribuer des revenus de transfert et d’apporter des services publics.", "Fonction de redistribution");
        Question E118 = new Question("* Vrai ou Faux : L’État providence a permis l’avènement de la Sécurité Sociale en France. ", "VRAI");
        Question E119 = new Question("** Présenter les fonctions de l’État.", " Il y en a 4 : fonctions régalienne, de redistribution, de régulation et de production + Explications");
        Question E120 = new Question("* Vrai ou Faux : Le marché des droits à polluer permet aux entreprises de polluer sans contraintes. ", "Faux, le dépassement d’un certain seuil les oblige à acheter des droits à polluer sur le marché.");
        Question E121 = new Question("** Proposer deux raisons expliquant pourquoi l’État prend en charge les externalités négatives.", "- Il est impossible d’identifier les agents économiques à l’origine des externalités négatives - La gestion de ces externalités est très coûteuse.");
        Question E122 = new Question("** Présenter les barrières qui peuvent exister à l’entrée d’un marché.", "Barrières technologiques, stratégiques, économiques, légales. + Explications.");
        Question E123 = new Question("** Indiquer les conséquences d’une augmentation durable et généralisée des prix.", " En période d’inflation, les ménages demandent des augmentations de salaires, ce qui augmente les coûts de production des entreprises, qui ont alors tendance à augmenter à leur tour le prix de leurs produits. Cercle vicieux.");
        Question E124 = new Question("* Vrai ou Faux : Adam SMITH est le père de la théorie des avantages relatifs. ", "Faux, C’est David RICARDO");
        Question E125 = new Question("* Préciser ce que signifie HOS dans la théorie du même nom.", " HOS est un acronyme reprenant l’initiale des 3 auteurs de la théorie : Heckscher, Ohlin et Samuelson.");
        Question E126 = new Question("** Expliquer pourquoi adopter une norme ou un standard dans le secteur informatique.", "Pour permettre l’interopérabilité des systèmes informatiques, donc améliorer la communication, la transmission d’informations entre différents SI, faciliter l’effet réseau et les rendements d’échelle,…");
        Question E127 = new Question("* Vrai ou Faux : Il peut y avoir croissance sans développement. ", "VRAI");
        Question E128 = new Question("* Nommer l’indicateur mesurant la croissance, le niveau de scolarisation et de santé d’un pays.", " L’indicateur de développement humain (IDH).");
        Question E129 = new Question("** Présenter un instrument permettant ", "de mesurer le développement durable. L’emprunte écologique ou le PIB vert + Explication.");
        Question E130 = new Question("* Citer les différents types de relations marchandes que les agents économiques peuvent entretenir entre eux.", "BtoB BtoC CtoC");
        Question E131 = new Question("*** Préciser pourquoi Apple a fait le choix de fabriquer les composants de son iPhone dans différents pays.", "Apple pratique la DIPP (division internationale du processus de production) afin de fabriquer chaque composant dans le pays garantissant le coût de production (notamment main-d’œuvre) le plus bas.");
        Question E132 = new Question("** Vision conçoit des jeux vidéo. Proposer 2 raisons expliquant que l’entreprise souhaite investir le marché des jeux pour smartphone.", "1/ Pour obtenir de nouveaux débouchés en accédant à un nouveau segment du marché du jeu vidéo. 2/ Pour faire face à la concurrence. 3/ Pour s’adapter à l’évolution de sa clientèle. 4/ Pour augmenter son chiffre d’affaires.");


        return new QuestionBank(Arrays.asList(E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, E14, E15, E16, E17, E18, E19, E20, E21, E22, E23, E24, E25,
                E26, E27, E28, E29, E30, E31, E32, E33, E34, E35, E36, E37, E38, E39, E40, E41, E42, E43, E44, E45, E46, E47, E48, E49, E50,
                E51, E52, E53, E54, E55, E56, E57, E58, E59, E60, E61, E62, E63, E64, E65, E66, E67, E68, E69, E70, E71, E72, E73, E74, E75,
                E76, E77, E78, E79, E80, E81, E82, E83, E84, E85, E86, E87, E88, E89, E90, E91, E92, E93, E94, E95, E96, E97, E98, E99, E100,
                E101, E102, E103, E104, E105, E106, E107, E108, E109, E110, E111, E112, E113, E114, E115, E116, E117, E118, E119, E120, E121, E122, E123, E124, E125,
                E126, E127, E128, E129, E130, E131, E132));
    }

}
