import { Routes } from '@angular/router';

import { DashboardComponent } from '../../dashboard/dashboard.component';
import { UserProfileComponent } from '../../user-profile/user-profile.component';
import { TableListComponent } from '../../table-list/table-list.component';
import { TypographyComponent } from '../../typography/typography.component';
import { IconsComponent } from '../../icons/icons.component';
import { MapsComponent } from '../../maps/maps.component';
import { NotificationsComponent } from '../../notifications/notifications.component';
import { UpgradeComponent } from '../../upgrade/upgrade.component';
//import { PruebaComponent } from 'app/page/prueba/prueba/prueba.component';
import { AuthGuard } from 'app/core/services/util/auth-guard.guard';
import { ClientComponent } from 'app/page/rc/client/client.component';
import { BranchComponent } from 'app/page/am/branch/branch.component';
import { UserComponent } from 'app/page/am/user/user.component';
import { MenuRolComponent } from 'app/page/am/menu-rol/menu-rol.component';
import { ErrorComponent } from 'app/authentication/error/error.component';

export const AdminLayoutRoutes: Routes = [
  
    { path: 'dashboard',      component: DashboardComponent, canActivate: [AuthGuard] },
    { path: 'user-profile',   component: UserProfileComponent, canActivate: [AuthGuard] },
    { path: 'table-list',     component: TableListComponent, canActivate: [AuthGuard] },
    { path: 'typography',     component: TypographyComponent, canActivate: [AuthGuard]  },
    { path: 'icons',          component: IconsComponent, canActivate: [AuthGuard] },
    { path: 'maps',           component: MapsComponent, canActivate: [AuthGuard]  },
    { path: 'notifications',  component: NotificationsComponent },
    //{ path: 'notifications',  component: PruebaComponent, canActivate: [AuthGuard] },
    { path: 'upgrade',        component: UpgradeComponent, canActivate: [AuthGuard] },
    { path: 'cliente',        component: ClientComponent, canActivate: [AuthGuard]  },
    { path: 'sucursal',        component: BranchComponent, canActivate: [AuthGuard] },
    { path: 'usuario',        component: UserComponent, canActivate: [AuthGuard] },
    { path: 'privilegios',        component: MenuRolComponent, canActivate: [AuthGuard] },
    
];
