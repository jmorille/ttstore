package eu.ttbox.batch.ingram.price;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.util.StringUtils;

import com.google.common.base.Joiner;

public enum PriceFieldEnum {

	IndAction, // A=ADD (nouvel enregistrement);C=CHANGE (changement dans l’enregistrement);D=DELETE
	BrandName, // Nom du fabricant tel qu'il est renseigné chez Ingram Micro
	BrandCodeIngram, // Ce code est le code du fabricant chez Ingram Micro.
	Sku, // Référence Ingram Micro (SKU)
	ProductDesc1, // Première ligne de description du produit
	ProductDesc2, // Seconde ligne de description du produit chez Ingram Micro
	ProcessorCode, // (MAC/PC, etc...)
	// CompanyCode, // ??
	PartNumber, // Référence du produit chez le fournisseur
	SupplierPrice, // Votre prix d'achat (format 9999999.99)
	PublicPrice, // Prix public (lorsqu'il est connu car certains fournisseurs ne communiquent pas de prix publics, dans ce cas, le prix indiqué est zéro).
					// Format 9999999.99
	Vide, // On sait pas INCONNU TODO
	IsInStock, // Indique si le produit est en stock
	ProductSubtitutionSku, // Indique la référence Ingram micro de substitution. Il peut s'agir d'un produit de remplacement qui va succéder au produit actuel
							// ou d'une promotion.
	ProductStatus, // Sert à indiquer si le produit est actif ou obsolète : A ou vide = produit actif */N/V/D = obsolète
	Ean, // (code UPC ou code EAN sur 13 caractères). Ce code est systématiquement vérifié pour un produit lorsqu'il est entré au moins une fois en stock.
	CategoryId, // Vous trouverez la table de correspondance entre le code à 4 chiffres contenu dans le fichier Price et la description en clair de la catégorie
				// dans le fichier newcats se trouvant dans le répertoire newcats
	CountryCode, // FR pour France. Indique dans quel pays le tarif est valable.
	Chiffre018, // On sait pas INCONNU TODO
	CrcCode, // Ce code vous indique pourquoi la référence du produit a été créée. L'information est utile lorsque vous avez plusieurs références Ingram Micro
				// avec la même référence fabricant. @see CrcCodeEnum
	ProductClassification, // Ce code vous indique comment le produit est classifié par Ingram Micro (voir la table ci-dessous avec la signification de chaque
							// code).
	PromoDateBegin, // Indique la date de début d'une promotion sur le produit.
	PromoDateEnd, // Indique la date de fin d'une promotion sur le produit.
	PromoMinQuantity, // Dans le cas d'une promotion "X produits gratuits pour Y produits achetés", indique la quantité minimum pour pouvoir bénéficier de la
						// promotion
	PromoProductFreeQty, // Dans le cas d'une promotion "X produits gratuits pour Y produits achetés", indique la quantité de produit gratuit offerte
	PropoProductFreeSku; // Dans le cas d'une promotion "X produits gratuits pour Y produits achetés", indique la référence Ingram Micro du produit gratuit

	public String readString(FieldSet fs) {
		return fs.readString(this.name());
	}

	public Date readDate(FieldSet fs) {
		return fs.readDate(this.name(), (Date) null);
	}

	public BigDecimal readBigDecimal(FieldSet fs) {
		return fs.readBigDecimal(this.name());
	}

	public Integer readInteger(FieldSet fs) {
		Integer defaultValue = null;
		String value = fs.readString(this.name());
		return StringUtils.hasLength(value) ? Integer.parseInt(value) : defaultValue;
	}

	public static String FIELDS_NAMES = Joiner.on(',').join(values());
	public static String FIELDS_NAMES_ADD_ALL = FIELDS_NAMES + ",ALL";

	public static String FIELDS_IDS = Sku.name();// Joiner.on(',').join(ShopId, TechProductId);

}
