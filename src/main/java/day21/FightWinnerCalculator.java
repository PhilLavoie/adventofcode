package day21;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FightWinnerCalculator {

    private static final Logger logger = LoggerFactory.getLogger(FightWinnerCalculator.class);

    Character winnerBetween(Character firstCharacter, Character secondCharacter) {
        int firstHitPoints = firstCharacter.getHitPoints();
        int secondHitPoints = secondCharacter.getHitPoints();

        int damagePerFirstCharacterAttack =
            Math.max(firstCharacter.getDamageScore() - secondCharacter.getArmorScore(), 1);
        int damagePerSecondCharacterAttack =
            Math.max(secondCharacter.getDamageScore() - firstCharacter.getArmorScore(), 1);

        int noAttacksToKillSecond = secondHitPoints / damagePerFirstCharacterAttack;
        if (secondHitPoints % damagePerFirstCharacterAttack != 0) {
            noAttacksToKillSecond++;
        }

        int noAttacksToKillFirst = firstHitPoints / damagePerSecondCharacterAttack;
        if (firstHitPoints % damagePerSecondCharacterAttack != 0) {
            noAttacksToKillFirst++;
        }

        logger.debug("number of attacks so that first character kills the second is {}",
            noAttacksToKillSecond);
        logger.debug("number of attacks so that second character kills the first is {}",
            noAttacksToKillFirst);

        if (noAttacksToKillSecond <= noAttacksToKillFirst) {
            return firstCharacter;
        }

        return secondCharacter;
    }
}
