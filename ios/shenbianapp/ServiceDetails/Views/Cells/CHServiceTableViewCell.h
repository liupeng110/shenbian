//
//  CHServiceTableViewCell.h
//  shenbianapp
//
//  Created by book on 2017/9/16.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "CHServiceCellModel.h"

typedef void(^ClickShopCart)();

@interface CHServiceTableViewCell : UITableViewCell

@property(nonatomic,strong)NSIndexPath *indexPath;
@property(nonatomic,strong) CHServiceCellModel *cellModel;

@property(nonatomic,copy) ClickShopCart clickShopCart;

@end
