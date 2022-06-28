package com.marstafk.IHMtrackerTool;

import com.marstafk.IHMtrackerTool.repositories.*;
import com.marstafk.IHMtrackerTool.services.ServiceTests;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runner.RunWith;
import org.junit.platform.suite.api.Suite;
@SelectPackages({"com.marstafk.IHMtrackerTool.repositories", "com.marstafk.IHMtrackerTool.services"})
@SuiteClasses({ClassroomRepositoryTest.class, FamilyRepositoryTest.class, GradeRepositoryTest.class, JogathonMasterRepositoryTest.class, PersonRepositoryTest.class, PersonTypeRepositoryTest.class, PledgeRepositoryTest.class,
PledgeTypeRepositoryTest.class, RunRepositoryTest.class, SponsorRepositoryTest.class, ServiceTests.class})
@Suite
class IhMtrackerToolApplicationTests {


}
