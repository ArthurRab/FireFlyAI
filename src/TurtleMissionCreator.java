public class TurtleMissionCreator implements MissionCreator {
<<<<<<< HEAD
    Point topLeft, bottomRight;

    public TurtleMissionCreator(Point start) {
        MissionManager.getInstance().clearAllMissions();
        int minX = 100, minY = 100, maxX = -100, maxY = -100;
        float mx, my;
        for (Point p : PlayerAI.world.getFriendlyNestPositions()) {
            Point temp = p.subtract(start);
            if (temp.getX() < minX) minX = temp.getX();
            if (temp.getX() > maxX) maxX = temp.getX();
            if (temp.getY() < minY) minY = temp.getY();
            if (temp.getY() > maxY) maxY = temp.getY();
        }
        topLeft = start.add(new Point(minX, minY)).getMod(PlayerAI.world.getWidth(), PlayerAI.world.getHeight());
        bottomRight = start.add(new Point(maxX, maxY)).getMod(PlayerAI.world.getWidth(), PlayerAI.world.getHeight());
=======
>>>>>>> parent of 99f6931... TURTLE



<<<<<<< HEAD
        for (int i = topLeft.getX(); i <= bottomRight.getX(); i++) {
            for (int j = topLeft.getY(); j <= bottomRight.getY(); j++) {
                float temp = ((i - mx) * (i - mx) + (j - my) * (j - my));
                MissionManager.getInstance().addMission(new HoldPositionQuicklyMission((int) temp, PlayerAI.world.getTileAt(new Point(i, j)), (float) (100 / temp)));
            }
        }
    }
=======
>>>>>>> parent of 99f6931... TURTLE

    @Override
    public void makeMissions() {

    }


}
