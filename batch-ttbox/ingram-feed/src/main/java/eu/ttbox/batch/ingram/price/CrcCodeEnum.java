package eu.ttbox.batch.ingram.price;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.transform.FieldSet;

import com.google.common.base.Strings;

public enum CrcCodeEnum {
	STD, // Produit standard // This is default for any new original product sku.
	KIT, // KIT/Bundle // Indique que la référence désigne un kit regroupant plusieurs autres produits. La référence fabricant du kit sera celle du produit le
			// plus cher parmi ses composants.
	BTO, // Built-to-Order // Le produit est destiné à un PC construit à la demande (par exemple Top Config). Ces produits ne peuvent pas être commandés en
			// dehors d'une configuration construite à la demande.
	PRV, // Promotion fabricant //Référence créée dans le cadre d'une promotion initiée par le fabricant.
	PRI, // Promotion Ingram Micro // Référence créée dans le cadre d'une promotion initiée par Ingram Micro
	PRC, // Produit pour un client/client final spécifique //Référence créée pour un client ou un groupe de clients particulier.
	PRS, // Pricing software // Produit logiciels Utilisé pour les règles spécifiques aux licences.
	UPC, // UPC code //Référence dupliquée pour un code UPC/EAN différent avec un même code fabricant.
	BRK, // Broker SKU //La référence est achetée chez un broker.
	KIB, // Kit Broker //Il s'agit d'un kit avec des produits achetés chez un brocker.
	OUT, // Référence d'externalisation logistique //Référence créée dans le cadre d'un projet d'externalisation logistique (IM stocke les produits appartenant
			// à un revendeur)
	DEM, // Produit démo //Produit de démonstration
	DEC, // Produit démo client //Produit de démonstration réservé à un client spécifique
	SNG, // Produit simple //Produits qu'Ingram achète par paquet et revend à l'unité (par exemple paquet de 100 cartes revendues une à une sans emballage)
	REF; // Produit réparé //Produit réparé

	private CrcCodeEnum() {
	}

	private static CrcCodeEnum[] SPECIFIC_SUPPLIER = new CrcCodeEnum[] { BTO, PRC, OUT, DEC };
	private static CrcCodeEnum[] IS_NOT_SELLABLE = new CrcCodeEnum[] { DEM, DEC, REF };
	private static CrcCodeEnum[] IS_KIT = new CrcCodeEnum[] { KIT, KIB };
	private static CrcCodeEnum[] IS_PROMO = new CrcCodeEnum[] { PRV, PRI };

	public List<CrcCodeEnum> sellableCodes;

	public static CrcCodeEnum readCrcCode(FieldSet fs) {
		CrcCodeEnum result = null;
		String value = fs.readString(PriceFieldEnum.CrcCode.name());
		if (!Strings.isNullOrEmpty(value)) {
			result = CrcCodeEnum.valueOf(value);
		}
		return result;
	}

	public boolean isLicence() {
		boolean isLicence = PRS == this;
		return isLicence;
	}

	public boolean isKit() {
		boolean isKit = KIT == this || KIB == this;
		return isKit;
	}

	public boolean isPromo() {
		boolean isKit = PRV == this || PRI == this;
		return isKit;
	}

	public boolean isSellable() {
		boolean isSellable = true;
		if (sellableCodes == null) {
			List<CrcCodeEnum> codes = new ArrayList<CrcCodeEnum>();
			for (CrcCodeEnum code : IS_NOT_SELLABLE) {
				codes.add(code);
			}
			for (CrcCodeEnum code : SPECIFIC_SUPPLIER) {
				codes.add(code);
			}
			this.sellableCodes = codes;
		}
		isSellable = !this.sellableCodes.contains(this);
		return isSellable;
	}

}
