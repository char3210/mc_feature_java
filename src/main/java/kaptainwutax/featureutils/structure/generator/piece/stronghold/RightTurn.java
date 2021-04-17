package kaptainwutax.featureutils.structure.generator.piece.stronghold;

import kaptainwutax.featureutils.structure.Stronghold;
import kaptainwutax.featureutils.structure.generator.StrongholdGenerator;
import kaptainwutax.seedutils.rand.JRand;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.util.block.BlockBox;
import kaptainwutax.mcutils.util.block.BlockDirection;

import java.util.List;

public class RightTurn extends Stronghold.Piece {

    public RightTurn(int pieceId, JRand rand, BlockBox boundingBox, BlockDirection facing) {
        super(pieceId);
        this.setOrientation(facing);
        rand.nextInt(5); //Random entrance.
        this.boundingBox = boundingBox;
    }

    public static RightTurn createPiece(List<Stronghold.Piece> pieces, JRand rand, int x, int y, int z, BlockDirection facing, int pieceId) {
        BlockBox box = BlockBox.rotated(x, y, z, -1, -1, 0, 5, 5, 5, facing.getRotation());
        return Stronghold.Piece.isHighEnough(box) && Stronghold.Piece.getNextIntersectingPiece(pieces, box) == null ? new RightTurn(pieceId, rand, box, facing) : null;
    }

    @Override
    public void populatePieces(StrongholdGenerator gen, Start start, List<Stronghold.Piece> pieces, JRand rand) {
        BlockDirection facing = this.getFacing();

        if (facing != BlockDirection.NORTH && facing != BlockDirection.EAST) {
            this.generateSmallDoorChildrenLeft(gen, start, pieces, rand, 1, 1);
        } else {
            this.generateSmallDoorChildRight(gen, start, pieces, rand, 1, 1);
        }
    }

    public boolean process(JRand rand, BPos pos) {
        skipWithRandomized(rand, 0, 0, 0, 4, 4, 4, true);
        // door not random
        // 1 call not random (direction dependant)
        return true;
    }

}
