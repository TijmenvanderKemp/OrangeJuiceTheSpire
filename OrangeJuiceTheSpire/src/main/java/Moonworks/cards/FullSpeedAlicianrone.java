package Moonworks.cards;

import Moonworks.OrangeJuiceMod;
import Moonworks.cards.abstractCards.AbstractDynamicCard;
import Moonworks.cards.abstractCards.AbstractNormaAttentiveCard;
import Moonworks.characters.TheStarBreaker;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static Moonworks.OrangeJuiceMod.makeCardPath;

public class FullSpeedAlicianrone extends AbstractNormaAttentiveCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Big Slap Deal 10(15)) damage.
     */

    // TEXT DECLARATION

    public static final String ID = OrangeJuiceMod.makeID(FullSpeedAlicianrone.class.getSimpleName());
    public static final String IMG = makeCardPath("FullSpeedAlicianrone.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheStarBreaker.Enums.COLOR_WHITE_ICE;

    private static final int COST = 1;
    private static final int DAMAGE = 8;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final int DRAW = 2;
    //private static final int UPGRADE_PLUS_DRAW = 1;

    private static final Integer[] NORMA_LEVELS = {3};

    // /STAT DECLARATION/


    public FullSpeedAlicianrone() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, NORMA_LEVELS);
        damage = baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = DRAW;
        //this.isMultiDamage = true;
    }
    @Override
    public float getTitleFontSize() {
        return 19F;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (getNormaLevel() >= NORMA_LEVELS[0]) {
            this.addToBot(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));
        } else {
            this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            //Time for some hacks
            /* See if this works, lol
            int index = AbstractDungeon.getMonsters().monsters.indexOf(m);
            this.addToBot(new DamageAction(m, new DamageInfo(p, multiDamage[index], damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            //*/
        }
        this.addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, magicNumber)));
    }

    @Override
    public void applyPowers() {
        this.target = getNormaLevel() >= NORMA_LEVELS[0] ? CardTarget.ALL_ENEMY : CardTarget.ENEMY;
        this.isMultiDamage = getNormaLevel() >= NORMA_LEVELS[0];
        super.applyPowers();
    }

    public void calculateCardDamage(AbstractMonster m) {
        this.isMultiDamage = getNormaLevel() >= NORMA_LEVELS[0];
        super.calculateCardDamage(m);
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            //upgradeMagicNumber(UPGRADE_PLUS_DRAW);
            initializeDescription();
        }
    }
}