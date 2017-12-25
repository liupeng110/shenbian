//
//  CHServiceTableViewCell.h
//  shenbianapp
//
//  Created by book on 2017/9/16.
//  Copyright © 2017 . All rights reserved.
//

#import <UIKit/UIKit.h>
#import "CHServiceCellModel.h"

typedef void(^ClickAddShopCart)(void);

@interface CHServiceTableViewCell : UITableViewCell

@property(nonatomic,strong)NSIndexPath *indexPath;
@property(nonatomic,strong) CHServiceCellModel *cellModel;

@property(nonatomic,copy) ClickAddShopCart clickAddShopCart;

@end
