package Moonworks.cards.tempCards;

import Moonworks.OrangeJuiceMod;
import Moonworks.actions.HealPercentileDamageAction;
import Moonworks.cards.abstractCards.AbstractDynamicCard;
import Moonworks.cards.abstractCards.AbstractTempCard;
import Moonworks.characters.TheStarBreaker;
import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

import static Moonworks.OrangeJuiceMod.makeCardPath;

public class MagicalRevenge extends AbstractTempCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Big Slap Deal 10(15)) damage.
     */

    // TEXT DECLARATION

    public static final String ID = OrangeJuiceMod.makeID(MagicalRevenge.class.getSimpleName());
    public static final String IMG = makeCardPath("MagicalRevenge.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheStarBreaker.Enums.COLOR_WHITE_ICE;

    private static final int COST = 0;
    private static final int HEAL_PERCENT = 33;
    private static final int UPGRADE_PLUS_HEAL_PERCENT = 17;

    // /STAT DECLARATION/


    public MagicalRevenge() {
        super(ID, IMG, COST, TYPE, COLOR, TARGET);
        magicNumber = baseMagicNumber = HEAL_PERCENT;
        //this.setDisplayRarity(CardRarity.RARE);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int dmg = p.maxHealth - p.currentHealth;
        this.addToBot(new HealPercentileDamageAction(p, m, new DamageInfo(p, dmg, damageTypeForTurn), magicNumber, AbstractGameAction.AttackEffect.FIRE));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_HEAL_PERCENT);
            //rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}