package Moonworks.cards.trapCards;

import Moonworks.OrangeJuiceMod;
import Moonworks.cards.abstractCards.AbstractNormaAttentiveCard;
import Moonworks.cards.abstractCards.AbstractTrapCard;
import Moonworks.characters.TheStarBreaker;
import Moonworks.powers.BigBangBellPower;
import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

import static Moonworks.OrangeJuiceMod.makeCardPath;
import static Moonworks.OrangeJuiceMod.makeID;

//@AutoAdd.Ignore
public class BigBangBell extends AbstractTrapCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * In-Progress Form At the start of your turn, play a TOUCH.
     */

    // TEXT DECLARATION

    public static final String ID = makeID(BigBangBell.class.getSimpleName());
    public static final String IMG = makeCardPath("BigBangBell.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheStarBreaker.Enums.COLOR_WHITE_ICE;

    private static final int COST = 1;
    //private static final int UPGRADE_REDUCED_COST = 1;
    private static final int STACKS = 10;
    private static final int UPGRADE_PLUS_STACKS = 5;

    private static final Integer[] NORMA_LEVELS = {3};

    // /STAT DECLARATION/


    public BigBangBell() {

        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, NORMA_LEVELS);
        this.magicNumber = this.baseMagicNumber = STACKS;
        //this.retain = true;
        //this.exhaust = true;

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new BigBangBellPower(m, p, this.magicNumber)));
    }

    @Override
    public void applyNormaEffects() {
        modifyMagicNumber(5, NORMA_LEVELS[0]);
        super.applyNormaEffects();
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //upgradeBaseCost(UPGRADE_REDUCED_COST);
            upgradeMagicNumber(UPGRADE_PLUS_STACKS);
            initializeDescription();
        }
    }
}
